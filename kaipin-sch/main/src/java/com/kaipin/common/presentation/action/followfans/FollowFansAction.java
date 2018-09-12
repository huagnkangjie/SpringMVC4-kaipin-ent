package com.kaipin.common.presentation.action.followfans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.ConstantTables;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.service.followfans.ICommFollowFansService;
import com.kaipin.university.service.feed.IFeedBaseService;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;

/**
 * 关注和粉丝
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/followfans")
public class FollowFansAction extends BaseAction{
	
	@Autowired
	private ISchBaseUserService schBaseUserInfoService;
	@Autowired
	private IFeedBaseService feedBaseService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchBaseUserService schBaseUserService;
	@Autowired
	private IBaseCodeService codeService;
	@Autowired
	private ICommFollowFansService commFollowFansService;
	
	public static String PAGE = "/foloowfans/";

	/**
	 * 
	 * @param page
	 * @param type 查询粉丝还是关注
	 * @param conditionUserType 更多关注的时候，只查询该条件的用户
	 * @return
	 */
	@RequestMapping("/getFollowList")
	@ResponseBody
	public Json getFollowList(HttpServletRequest request, User user, String page, String pageSize, 
			String type, String toUserType, String fromUserType ){
		Json json = new Json();
		try {
			String cookie_sid = super.getOrgId(request, user);
			
			if(StringUtil.isEmpty(page)){
				page = Constant.VALUE_ONE;
			}
			if(StringUtil.isEmpty(pageSize)){
				pageSize = Constant.VALUE_EIGHT;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			String type_col = "";
			String id_col = "";
			String count = "";
			if(type.equals("follow")){//获取关注
				map.put("from_uid", cookie_sid);
				type_col = "to_user_type";
				id_col = "to_uid";
				
				if(StringUtil.isNotEmpty(toUserType)){
					map.put(type_col, toUserType);//查询关注用户的类型，用于企业资料的关注用户列表展示
				}
				
				List<Map<String,Object>> list = schBaseUserInfoService.getFollowAndFans(map);
				if(list.size() > 0){
					count = list.get(0).get(Constant.COUNT)+"";//关注总数
				}
				
			}else if(type.equals("fans")){//获取粉丝
				map.put("to_uid", cookie_sid);
				type_col = "from_user_type";
				id_col = "from_uid";
				
				List<Map<String,Object>> list2 = schBaseUserInfoService.getFollowAndFans(map);
				if(list2.size() > 0){
					count = list2.get(0).get(Constant.COUNT) + "";//粉丝总数
				}
			}
			map.put("page_start", (Integer.valueOf(page) - 1) * Integer.valueOf(pageSize));
			map.put("page_size", Integer.valueOf(pageSize));
			
			List<Map<String,Object>> list = schBaseUserInfoService.getFollowAndFansList(map);
			if(list.size()>0){
				Map<String,Object> resultMap = new HashMap<String,Object>();
				List<Map<String,Object>> resultList = getDetail(list, type_col, id_col);
				if(resultList != null && resultList.size() > 0){
					resultMap.put("person", resultList);
					resultMap.put("count", count);
					json.setObj(resultMap);
					json.setSuccess(true);
				}
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 返回封装的数据
	 * @param list
	 * @param type_col
	 * @return
	 */
	public List<Map<String,Object>> getDetail(List<Map<String,Object>> list, String type_col, String id_col){
		try {
			List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
			if(list.size() > 0){
				String orgId = "",userType="", uType= "", name = "", logo = "", rowTwo = "", rowThree= "";
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object> resultMap = new HashMap<String, Object>();
					uType = list.get(i).get(type_col) + "";
					
					//获取企业的封装数据
					if(uType.equals(Constant.USER_TYPE_ENT)){
						List<Map<String,Object>> entList = 
								feedBaseService.getCompanyInfo(list.get(i).get(id_col)+"");
						
						name = entList.get(0).get("ent_simple_name")+"";
						if(StringUtil.isEmpty(name)){
							name = entList.get(0).get("ent_name")+"";
						}
						orgId = entList.get(0).get("id")+"";
						logo = getLogo(entList.get(0).get("logo")+"");
						rowTwo = getNameByCode(ConstantTables.COMM_INDUSTRY, 
								ConstantTables.INDUSTRY_COL_CODE, entList.get(0).get("industry_code")+"", 
								ConstantTables.INDUSTRY_COL_NAME);//对应企业行业
						rowThree = getArea(entList.get(0).get("office_area")+"");//对应企业所在地区
						userType = Constant.USER_TYPE_ENT;
						
					//获取学校的封装数据
					}else if(uType.equals(Constant.USER_TYPE_SCH)){
						List<Map<String,Object>> schList = 
								feedBaseService.getSchInfo(list.get(i).get(id_col)+"");
						
						name = getNameByCode(ConstantTables.COMM_SCHOOL, 
								ConstantTables.SCHOOL_COL_CODE, schList.get(0).get("school_code")+"", 
								ConstantTables.SCHOOL_COL_NAME);
						orgId = schList.get(0).get("id") + "";//组织id
						logo = getLogo(schList.get(0).get("school_logo")+"");//logo
						rowTwo = schList.get(0).get("school_short_name") + "";//学校简称
						rowThree = getArea(schList.get(0).get("location_code")+"");//对应企业所在地区
						userType = Constant.USER_TYPE_SCH;
						
					//获取学生的封装数据
					}else if(uType.equals(Constant.USER_TYPE_STU)){
						List<Map<String,Object>> stuList = 
								feedBaseService.getStuInfo(list.get(i).get(id_col)+"");
						if(stuList.size() > 0){
							name = StringUtil.getResumeName(stuList.get(0).get("surname")+"", stuList.get(0).get("miss_surname")+"");
							orgId = stuList.get(0).get("id") + "";//组织id
							logo = getLogo(stuList.get(0).get("head_url")+"");//logo
							rowTwo = getNameByCode(ConstantTables.COMM_SCHOOL, 
									ConstantTables.SCHOOL_COL_CODE, stuList.get(0).get("school_code")+"", 
									ConstantTables.SCHOOL_COL_NAME);//学校名称
							rowThree = getArea(stuList.get(0).get("location_code")+"");//对应企业所在地区
							userType = Constant.USER_TYPE_STU;
						}
					}
					
					resultMap.put("name", name);
					resultMap.put("orgId", orgId);
					resultMap.put("userType", userType);
					resultMap.put("logo", logo);//logo
					resultMap.put("rowTwo", rowTwo);
					resultMap.put("rowThree", rowThree);
					resultList.add(resultMap);
				}
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getLogo(String logo){
		if(logo.endsWith("null")){
			logo = Constant.VALUE_ZERO;
		}else{
			if(StringUtil.isEmpty(logo)){
				logo = Constant.VALUE_ZERO;
			}else{
				if(!logo.startsWith("http://")){
					PropUtil pro = new PropUtil();
					String ip = pro.getValue(Constant.STU_HEAD_URL);
					logo = ip + logo;
				}
			}
			
		}
		return logo;
	}
	
	public String getNameByCode(String tableName, String columnName, String cvalue, String backColunm){
		String name = "";
		try {
			if(StringUtil.isNotEmpty(cvalue)){
				name = codeService.getNameByCode(tableName, columnName, cvalue, backColunm);
			}
			return name;
		} catch (Exception e) {
			return name;
		}
	}
	
	public String getArea(String code){
		String name = "";
		try {
			if(StringUtil.isNotEmpty(code)){
				name = codeService.getAreaAllNameByCode(code);
			}
			return name;
		} catch (Exception e) {
			return name;
		}
	}
	
	/**
	 * 添加关注
	 * @param fromId
	 * @param toId
	 * @return
	 */
	@RequestMapping(value="addPush")
	@ResponseBody
	public Json addPush(HttpServletRequest request, String toId , String toUserTypes){
		Json json = new Json();
		try {
			
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			String ids[] = toId.split(",");
			String toUserType[] = toUserTypes.split(",");
			for (int i = 0; i < ids.length; i ++) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", UuidUtil.getUUID());
				map.put("from_uid", cookie_sid);
				map.put("to_uid", ids[i]);
				map.put("remark", "");
				map.put("create_time", TimeUtil.currentTimeMillis());
				map.put("from_user_type", localUserService.selectByPrimaryKey(cookie_uid).getCategoryId());
				map.put("to_user_type", toUserType[i]);
				List<Map<String,Object>> list = schBaseUserInfoService.checkIsFollow(map);
				if(list.size() == 0 && (!cookie_sid.equals(ids[i]))){
					Map<String,Object> param = new HashMap<String,Object>();
					param.put("from_uid", ids[i]);
					param.put("to_uid", cookie_sid);
					List<Map<String,Object>> listCheck = schBaseUserInfoService.checkIsFollow(param);
					if(listCheck.size() > 0){
						param.put("relation", "1");
						map.put("relation", "1");
					}else{
						param.put("relation", "0");
						map.put("relation", "0");
					}
					//更新原来的关注关系
					schBaseUserInfoService.updatePushFoloow(param);
					
					//插入关注
					schBaseUserInfoService.addPushFoloow(map);
					
					//更新统计表
					boolean flag = commFollowFansService.insertFollowCount(cookie_sid);
					boolean flagFans = commFollowFansService.insertFollowCount(toId);
					if(!flag){
						Map<String,Object> maps = new HashMap<String,Object>();
						maps.put("org_id", cookie_sid);
						commFollowFansService.updateFollowCount(maps);
					}
					if(!flagFans){
						Map<String,Object> maps = new HashMap<String,Object>();
						maps.put("org_id", toId);
						commFollowFansService.updateFollowCount(maps);
					}
					
					
				}
			}
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 取消关注
	 * @param fromId
	 * @param toId
	 * @return
	 */
	@RequestMapping(value="delPush")
	@ResponseBody
	public Json delPush(HttpServletRequest request, String fromId, String toId, String id, String userId){
		Json json = new Json();
		try {
			
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtil.isNotEmpty(id)){
				map.put("id", id);
			}else if(StringUtil.isNotEmpty(cookie_sid) && StringUtil.isNotEmpty(toId)){
				map.put("fromId", cookie_sid);
				map.put("toId", toId);
			}else{
				map.put("id", UuidUtil.getUUID());
			}
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("from_uid", toId);
			param.put("to_uid", cookie_sid);
			
			List<Map<String,Object>> listCheck = schBaseUserInfoService.checkIsFollow(param);
			
			if(listCheck.size() > 0){
				param.put("relation", "0");
				map.put("relation", "0");
			}else{
				param.put("relation", "0");
			}
			
			//更新原来的关注关系
			schBaseUserInfoService.updatePushFoloow(param);
			
			schBaseUserInfoService.delPushFoloow(map);
			
			//更新统计表
			boolean flag = commFollowFansService.insertFollowCount(cookie_sid);
			boolean flagFans = commFollowFansService.insertFollowCount(toId);
			if(!flag){
				Map<String,Object> maps = new HashMap<String,Object>();
				maps.put("org_id", cookie_sid);
				commFollowFansService.updateFollowCount(maps);
			}
			if(!flagFans){
				Map<String,Object> maps = new HashMap<String,Object>();
				maps.put("org_id", toId);
				commFollowFansService.updateFollowCount(maps);
			}
			
			//添加推荐记录
			Map<String, Object> mapRcord = new HashMap<>();
			mapRcord.put("obj_id", cookie_sid);
			mapRcord.put("relate_obj_id", toId);
			mapRcord.put("create_time", TimeUtil.currentTimeMillis());
			schBaseUserService.insertFollowRecommendStu(mapRcord);
			
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 粉丝和关注的详细列表页面
	 * @param request
	 * @param oper 粉丝还是关注者
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(HttpServletRequest request, String oper, User user, Model model, String toUserType){
		try {
			
			model.addAttribute("org_id", super.getOrgId(request, user));
			
			String title = "";
			
			if(oper.equals("follow")){
				title = "关注";
			}else{
				title = "粉丝";
			}
			
			model.addAttribute("toUserType", toUserType);
			
			this.setSysAttr(model, title, null, null);
			
			return PAGE + oper;
		} catch (Exception e) {
			e.printStackTrace();
			return PAGE + oper;
		}
	}
	
	public String getFollowFansCount(String org_id){
		Object floowCount = "0", fansCount = "0";
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String cookie_sid = org_id;
			
			if(StringUtil.isNotEmpty(cookie_sid)){
				map.put("from_uid", cookie_sid);
				List<Map<String,Object>> list = schBaseUserInfoService.getFollowAndFans(map);
				if(list.size() > 0){
					floowCount = list.get(0).get(Constant.COUNT);//关注总数
				}
				map.clear();
				map.put("to_uid", cookie_sid);
				List<Map<String,Object>> list2 = schBaseUserInfoService.getFollowAndFans(map);
				if(list2.size() > 0){
					fansCount = list2.get(0).get(Constant.COUNT);//粉丝总数
				}
			}
			
			map.clear();
			map.put("floowCount", floowCount);
			map.put("fansCount", fansCount);
			return floowCount+","+fansCount;
		} catch (Exception e) {
			e.printStackTrace();
			return "0,0";
		}
	}
}
