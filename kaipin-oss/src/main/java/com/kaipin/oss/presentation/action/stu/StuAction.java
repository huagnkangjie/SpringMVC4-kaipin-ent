package com.kaipin.oss.presentation.action.stu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.PagesPerMinute;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.oss.common.page.GenericDefaultPage;
import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.common.pojo.Json;
import com.kaipin.oss.common.pojo.StautsBean;
import com.kaipin.oss.common.web.CookieUtils;
import com.kaipin.oss.constant.AppSearchConstant;
import com.kaipin.oss.constant.Constant;
import com.kaipin.oss.model.common.UserLocalauth;
import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.model.sch.SchBaseInfo;
import com.kaipin.oss.model.stu.StuBaseInfo;
import com.kaipin.oss.model.stu.StuUser;
import com.kaipin.oss.presentation.action.Constants;
import com.kaipin.oss.service.stu.IStuBaseInfoService;
import com.kaipin.oss.service.stu.IStuUserService;
import com.kaipin.oss.service.stu.IUserLocalauthService;
import com.kaipin.oss.util.HttpPostUtil;
import com.kaipin.oss.util.JsonUtil;
import com.kaipin.oss.util.LuceneUtil;
import com.kaipin.oss.util.PropUtil;
import com.kaipin.oss.util.StringUtil;
import com.kaipin.oss.util.TimeUtil;
import com.kaipin.oss.util.UuidUtil;

/**
 * 学生管理
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/management/stu/main")
public class StuAction {
	
	private final String LIST = "stu/manager/list";//学生管理列表
	private final String VIP_LIST = "stu/manager/vip_list";//学生VIP管理列表
	private final String ADD = "stu/add";//新增页面
	private final String DETAIL = "stu/manager/detail";//学生详情
	private final String ZZSH_LISt = "stu/zzsh/list";//学生资质审核
	private final String ZZXQ = "sch/zzsh/zzxq";//学生资质详情
	private final String ONE_TO_ONE = "stu/manager/oneToOne";//学生一对一服务页面
	
	
	
	@Autowired
	private IStuBaseInfoService stuBaseInfoService;
	@Autowired
	private IStuUserService stuUserService;
	@Autowired
	private IUserLocalauthService localUserService;
	
	/**
	 * 学生基本信息管理
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest   request, String userName, String startTime, String endTime, 
			Integer pageNo , ModelMap model) {

		try {
			
			Map<String,Object> param = new HashMap<String,Object>();
			
			if(StringUtil.isNotEmpty(userName)){
				param.put("userName", userName.trim());
			}
			
			if(StringUtil.isEmpty(startTime)){
				startTime = "";
			}
			if(StringUtil.isEmpty(endTime)){
				endTime = "";
			}
			
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			
			int pageSize=	CookieUtils
					.getPageSize(request);
			
			IGenericPage<StuBaseInfo> pages = 
					stuBaseInfoService.getStuList(param, GenericDefaultPage.cpn(pageNo),
							pageSize);

			model.addAttribute(Constants.PAGE, pages);
			model.addAttribute("userName", userName);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			return LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return LIST;
		}
		
	}
	
	
	/**
	 * 学生 VIP 管理
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/viplist", method = { RequestMethod.GET, RequestMethod.POST })
	public String viplist(HttpServletRequest   request, String userName, String startTime, String endTime, 
			Integer pageNo , ModelMap model) {
		
		try {
			
			Map<String,Object> param = new HashMap<String,Object>();
			
			if(StringUtil.isNotEmpty(userName)){
				param.put("userName", userName.trim());
			}
			
			if(StringUtil.isEmpty(startTime)){
				startTime = "";
			}
			if(StringUtil.isEmpty(endTime)){
				endTime = "";
			}
			
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			
			param.put("vipCount", 1000);//默认前1000名注册用户为vip用户, 验证码不是空的也是VIP
			
			int pageSize=	CookieUtils
					.getPageSize(request);
			
			IGenericPage<StuBaseInfo> pages = 
					stuBaseInfoService.getStuVipList(param, GenericDefaultPage.cpn(pageNo),
							pageSize);
			
			model.addAttribute(Constants.PAGE, pages);
			model.addAttribute("userName", userName);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			return VIP_LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return VIP_LIST;
		}
		
	}
	
	/**
	 * 一对一服务的页面
	 * @return
	 */
	@RequestMapping("/ontoone")
	public String ontoone(String userId, String userName, Model model, String serviceCount){
		try {
			
			StuUser stuUser = stuUserService.selectByPrimaryKey(userId);
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			
			//获取求职意向列表
			List<Map<String,Object>> positionList = stuBaseInfoService.getLikePositionList(param);
			
			String positionListTip = "";
			if(positionList.size() == 0){
				positionListTip = "未填写求职意向";
			}
			
			
			
			//获取推送次数
			model.addAttribute("oneToOneCount", stuBaseInfoService.getOneToOneCount(userId));
			//获取推送职位的总此时
			model.addAttribute("oneToOnePositionCount", stuBaseInfoService.getOneToOnePositionCount(userId));
			
			model.addAttribute("userName", stuUser.getSurname() + stuUser.getMissSurname());
			model.addAttribute("logo",StringUtil.getLogo(stuUser.getHeadUrl()));
			
			model.addAttribute("positionList",positionList);
			model.addAttribute("positionListTip",positionListTip);
			model.addAttribute("userId",userId);
			
			return ONE_TO_ONE;
		} catch (Exception e) {
			e.printStackTrace();
			return ONE_TO_ONE;
		}
		
	}
	
	
	/**
	 * 获取职位列表
	 * @return
	 */
	@RequestMapping("/positionList")
	@ResponseBody
	public Json getPositionList(String positionName, String locationCode){
		Json json = new Json();
		try {
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyword", positionName);
			map.put("location_code", locationCode);
			String result = HttpPostUtil.doPost(pro.getValue(AppSearchConstant.SEARCH_URL) + AppSearchConstant.SEARCH_POSITION, map);
			result.replace("class=\\\"highlight\\\"", "class=highlight");//返回结果
			json.setObj(result);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 发送推荐职位
	 * @return
	 */
	@RequestMapping("/sendPushPositions")
	@ResponseBody
	public Json sendPushPositions(String pids, String userId){
		Json json = new Json();
		try {
			String ids[] = pids.split(",");
			Map<String, Object> map = new HashMap<>();
			String groupId = UuidUtil.getUUID();
			map.put("id", groupId);
			map.put("create_time", TimeUtil.currentTimeMillis());
			map.put("group_name", TimeUtil.currentTimeMillis());
			map.put("uid", userId);
			boolean flag = stuBaseInfoService.insertVipRecodGroup(map);
			if(flag){
				for (String id : ids) {
					map.clear();
					map.put("id", UuidUtil.getUUID());
					map.put("create_time", TimeUtil.currentTimeMillis());
					map.put("status", 0);
					map.put("position_id", id);
					map.put("group_id", groupId);
					stuBaseInfoService.insertVipRecod(map);
				}
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	
	/**
	 *企业用户禁用
	 * @param request
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/enable", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Json enable(HttpServletRequest   request, String userId, String oper) {
		Json json = new Json();
		try {
			if(StringUtil.isNotEmpty(userId)){
				UserLocalauth info = new UserLocalauth();
				info.setId(userId);
				info.setEnable(Byte.valueOf(oper));
				int flag = localUserService.updateByPrimaryKeySelective(info);
				
				//创建索引
				if(flag == 1){
					if(oper.equals(Constant.VALUE_ZERO)){//拉黑
						
						LuceneUtil.luceneOpt(userId, Constant.VALUE_THREE, Constant.VALUE_ONE);
						
					}else if(oper.equals(Constant.VALUE_ONE)){//取消拉黑
						
						LuceneUtil.luceneOpt(userId, Constant.VALUE_THREE, Constant.VALUE_ZERO);
					}
					json.setSuccess(true);
				}
				
				
				json.setObj(info);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	
	public void createIndex(String userId, StuUser user){
		try {
			Map<String, Object> mapLunece = new HashMap<String, Object>();
			String luneceTaskId = UuidUtil.getUUID();
			mapLunece.put("id", luneceTaskId);
			mapLunece.put("obj_id", userId);//资源id
			mapLunece.put("obj_type", 3);//对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
			mapLunece.put("opt", 0);//操作类型(0-add,1-delete,2-update
			mapLunece.put("create_time", TimeUtil.currentTimeMillis()+"");//
			mapLunece.put("status", 0);//处理状态（0-未处理,1-已处理
			mapLunece.put("handle_time", TimeUtil.currentTimeMillis()+"");//处理时间
			
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			
			String result = HttpPostUtil.doPost(pro.getValue(AppSearchConstant.SEARCH_URL) 
					+ AppSearchConstant.SEARCH_TASK_URL, mapLunece);
			
			String code = JsonUtil.jsonToObj(result, StautsBean.class).getCode();
			
			if(StringUtil.isNotEmpty(code) && code.equals(Constant.VALUE_ZERO)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", userId);
				param.put("surname", user.getSurname());
				param.put("miss_surname", user.getMissSurname());
				param.put("last_updated_time", user.getCreateTime());
				
				String s = HttpPostUtil.doPost(pro.getValue(AppSearchConstant.SEARCH_URL) 
						+"/lucene"+ AppSearchConstant.APP_SEARCH_STU_CREATE + luneceTaskId, param);
				System.out.println("stu 索引创建信息： " + s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}



