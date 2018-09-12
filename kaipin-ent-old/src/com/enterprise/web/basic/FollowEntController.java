package com.enterprise.web.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.ConstantTables;
import com.common.page.Page;
import com.common.pojo.Json;
import com.enterprise.model.common.EntUser;
import com.enterprise.service.common.IBaseCodeService;
import com.enterprise.service.common.IBaseService;
import com.enterprise.service.follow.IFollowEntService;
import com.util.CookieUtil;
import com.util.LogUtil;
import com.util.PropUtil;
import com.util.StringUtil;

@Controller
@RequestMapping("/followEntController")
public class FollowEntController {

	Logger log = Logger.getLogger(FollowEntController.class.getName());
	@Autowired
	private IFollowEntService followService;
	@Autowired
	private IBaseService baseService;
	@Autowired
	private IBaseCodeService baseCodeService;
	
	/**
	 * 获取企业关注数
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getFollowCounts")
	@ResponseBody
	public Json getFollowCounts(HttpServletRequest request, Page page){
		try {
			Json json = new Json();
//			page.setRows(8);
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("companyId", cookie_companyId);
//			map.put("page_start", (page.getPage()-1) * page.getRows());
//			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = followService.getFollowCount(map);
			String counts = "0";
			if(list.size() > 0){
				counts = String.valueOf(list.get(0).get("counts"));
				json.setSuccess(true);
			}else{
				json.setSuccess(false);
			}
			json.setObj(counts);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}
	
	/**
	 * 获取企业最新关注列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getFollowList")
	@ResponseBody
	public Json getFollowList(HttpServletRequest request, Page page, String num){
		try {
			Json json = new Json();
			List<Map<String,Object>> listStu = getList(request, page, num);
			//获取粉丝总数
			String counts = "0";
			
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("companyId", cookie_companyId);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> listCount = followService.getFollowCount(map);
			if(listCount.size() > 0){
				counts = String.valueOf(listCount.get(0).get("counts"));
			}
			
			if(listStu.size() > 0){
				json.setSuccess(true);
				map.put("vList", listStu);
				map.put("counts", counts);
			}
			
			json.setObj(map);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}
	
	/**
	 * 粉丝列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/followListPage")
	public String followListPage(HttpServletRequest request, Page page, Model model){
		try {
//			page.setPage(1);
//			List<Map<String,Object>> list = getList(request, page, "24");
//			model.addAttribute("followLists", list);
			return "/ent/basic/follow_list";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}

	/**
	 * 根据区域code获取名称
	 * @param areaCode
	 * @return
	 */
	public String getAreaName(String areaCode){
		String areaName = "";
		try {
			if(StringUtil.isNotEmpty(areaCode)){
				if(areaCode.equals("561") 
						|| areaCode.equals("562") 
						|| areaCode.equals("563")){
					int code = Integer.valueOf(areaCode);
					switch (code) {
					case 561:
						areaName = "香港";
						break;
					case 562:
						areaName = "澳门";
						break;
					case 563:
						areaName = "台湾省";
						break;
					default:
						areaName = "";
					}
				}else{
					String area2 = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, areaCode, ConstantTables.AREA_COL_NAME);
					String parentCode = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, areaCode, ConstantTables.COL_PARENT_CODE);
					String area1 = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, parentCode, ConstantTables.AREA_COL_NAME);
					areaName = area1 + " " + area2;
				}
			}
			return areaName;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			return areaName;
		}
	}
	
	/**
	 * 根据code返回名称
	 * @return
	 */
	public String getCodeName(String tableName, String colName, String code, String backName){
		String name = "";
		try {
			name = baseCodeService.getNameByCode(tableName, colName, code, backName);
			return name;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			return name;
		}
	}
	
	public List<Map<String,Object>> getList(HttpServletRequest request, Page page, String num){
		List<Map<String,Object>> listStu = null;
		try {
			
			int nums = 8;
			if(StringUtil.isNotEmpty(num)){
				nums = Integer.valueOf(num);
			}
			page.setRows(nums);
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("companyId", cookie_companyId);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = followService.getFollowCountList(map);
			
			PropUtil pro = new PropUtil();
			String path = pro.getValue(Constant.STU_HEAD_URL);
			listStu = new ArrayList<Map<String,Object>>();
			if(list.size() > 0){
				for(int i = 0; i < list.size(); i++){
					Map<String,Object> mapVal = new HashMap<String,Object>();
					Object head_url = list.get(i).get("head_url");
					String headUrl = "";
					if(head_url != null){
						headUrl = String.valueOf(head_url);
						if(!headUrl.contains("http://")){
							headUrl = path + head_url;
						}
					}
					//获取区域
					Object areaO = list.get(i).get("location_code");
					String areaName = "";
					if(areaO != null){
						areaName = getAreaName(String.valueOf(areaO));
					}
					//获取学习名称
					Object schoolO = list.get(i).get("school_code");
					String schoolCode = "";
					String schoolName = "";
					if(schoolO != null){
						schoolCode = String.valueOf(schoolO);
						if(StringUtil.isNotEmpty(schoolCode))
						schoolName = getCodeName("school_info", 
								"school_code", schoolCode, "school_name");
					}
					//获取学习名称
					Object zhuanyeO = list.get(i).get("major_code");
					String zhuanyeCode = "";
					String zhuanyeName = "";
					if(zhuanyeO != null){
						zhuanyeCode = String.valueOf(zhuanyeO);
						if(StringUtil.isNotEmpty(schoolCode))
							zhuanyeName = getCodeName("comm_major", 
									"major_code", zhuanyeCode, "major_name");
					}
					//获取学历
					Object educationO = list.get(i).get("education_code");
					String educationCode = "";
					String educationName = "无";
					if(educationO != null){
						educationCode = String.valueOf(educationO);
						if(StringUtil.isNotEmpty(schoolCode))
							educationName = getCodeName("comm_education", 
									"education_code", educationCode, "education_name");
					}
					
					Object nickNameO = list.get(i).get("nick_name");
					String nickName = "";
					if(nickNameO != null){
						nickName = String.valueOf(nickNameO);
						if(nickName.contains(",")){
							nickName = nickName.replace(",", "");
						}
					}
					
					
					
					mapVal.put("uId", list.get(i).get("id"));
					mapVal.put("head_url", headUrl);
					mapVal.put("nick_name", nickName);
					mapVal.put("areaName", areaName);
					mapVal.put("schoolName", schoolName);
					mapVal.put("zhuanyeName", zhuanyeName);
					mapVal.put("educationName", educationName);
					
					listStu.add(mapVal);
				}
			}
			return listStu;
		} catch (Exception e) {
			e.printStackTrace();
			return listStu;
		}
	}
	
}
