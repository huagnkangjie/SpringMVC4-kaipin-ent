package com.kaipin.common.presentation.action.push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.ConstantTables;
import com.kaipin.common.entity.Json;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.student.service.user.IStuUserService;

/**
 * 推荐公用类
 * @author Mr-H
 *
 */

@RequestMapping("/push")
@Controller
public class PushAction {
	
	public static String PAGE = "page"; //页码
	public static String PAGE_SIZE = "pageSize"; //每页多少条数据
	
	public static int DEFUALT_PAGE = 0;
	
	@Autowired
	private ISchBaseUserService schBaseUserService;
	@Autowired
	private IBaseCodeService codeService;
	@Autowired
	private ISchoolInfoLinkService linkService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private IStuUserService stuUserService;

	
	/**
	 * 获取首页推荐列表
	 * @param ids 第一次查询  / 换一个 
	 * @return
	 */
	@RequestMapping("/getPushList")
	@ResponseBody
	public Json getPush(HttpServletRequest request,String id, String ids, String type){
		Json json = new Json();
		try {
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			//返货封装后的数据
			List<List<Map<String,Object>>> resultList
							= new ArrayList<List<Map<String,Object>>>();
			
			int rowEnt = 3, rowStu = 1, rowSch = 1;
			
			List<Map<String,Object>> entlist = new ArrayList<Map<String,Object>>();//企业3条
			List<Map<String,Object>> schlist = new ArrayList<Map<String,Object>>();//学生1条
			List<Map<String,Object>> stulist = new ArrayList<Map<String,Object>>();//学校1条
			
			if(StringUtil.isNotEmpty(ids)){
				ids = getIds(ids);	
			}
			
			if(StringUtil.isNotEmpty(type) && !type.equals("init")){//点击换一个
				Map<String, Object> map = new HashMap<>();
				map.put("obj_id", cookie_sid);
				map.put("relate_obj_id", id);
				map.put("create_time", TimeUtil.currentTimeMillis());
				if(type.equals("11")){
					//添加推荐记录
					schBaseUserService.insertFollowRecommendEnt(map);
					
					entlist = getPushEntList(cookie_sid, cookie_uid, ids, 1);//更换 企业1条
				}else if(type.equals("12")){
					
					//添加推荐记录
					schBaseUserService.insertFollowRecommendSch(map);
					
					schlist = getPushSchList(cookie_sid, cookie_uid, ids, 1);//更换 学生1条
				}else if(type.equals("10")){
					
					//添加推荐记录
					schBaseUserService.insertFollowRecommendStu(map);
					
					stulist = getPushStuList(cookie_sid, cookie_uid, ids, 1);//更换 学校1条
				}
			}else{
				entlist = getPushEntList(cookie_sid, cookie_uid, ids, 1);//企业1条
				schlist = getPushSchList(cookie_sid, cookie_uid, ids, 1);//学校1条
				stulist = getPushStuList(cookie_sid, cookie_uid, ids, 3);//学生3条
			}
			
			resultList.add(entlist);
			resultList.add(schlist);
			resultList.add(stulist);
			json.setObj(resultList);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 封装数据  企业
	 * @return
	 */
	public List<Map<String,Object>> getPushEntList(String orgId, String uid, String ids, int rows){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("ids", ids);
			param.put("orgId", orgId);
			param.put(PAGE, DEFUALT_PAGE);
			param.put(PAGE_SIZE, rows);
			List<Map<String,Object>> list = schBaseUserService.getPushEntList(param);
			
			int length = list.size();
			if(length > 0){
				rows = length > rows ? rows : length;
				for (int i = 0; i < rows; i++) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("dataType", Constant.USER_TYPE_ENT);//数据类型，用于前台判断
					map.put("orgId", list.get(i).get("id"));//组织id
					map.put("name", getEntName(list.get(i).get("ent_simple_name")+"",list.get(i).get("ent_name")+""));//名称
					map.put("logo", getLogo(list.get(i).get("logo")+""));//logo
					map.put("rowTwo", getNameByCode(ConstantTables.COMM_INDUSTRY, 
							ConstantTables.INDUSTRY_COL_CODE, list.get(i).get("industry_code")+"", 
							ConstantTables.INDUSTRY_COL_NAME));//对应企业行业
					map.put("rowThree", getArea(list.get(i).get("office_area")+""));//对应企业所在地区
					resultList.add(map);
				}
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		}
	}
	
	/**
	 * 封装数据  学校
	 * @return
	 */
	public List<Map<String,Object>> getPushSchList(String orgId, String uid, String ids, int rows){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			UserLocalauth user = localUserService.selectByOrgId(orgId);
			String uType = user.getCategoryId();
			String locationCode = "";
			if(uType.equals(Constant.USER_TYPE_ENT)){
				CompanyInfo info = companyInfoService.selectByPrimaryKey(orgId);
				locationCode = info.getOfficeArea();
			}else if(uType.equals(Constant.USER_TYPE_SCH)){
				locationCode = linkService.selectByPrimaryKey(orgId).getLocationCode();
			}else if(uType.equals(Constant.USER_TYPE_STU)){
				locationCode = stuUserService.selectByPrimaryKey(orgId).getLocationCode();
			}
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("ids", ids);
			param.put("orgId", orgId);
			param.put(PAGE, DEFUALT_PAGE);
			param.put(PAGE_SIZE, rows);
			param.put("location_code", locationCode);
			List<Map<String,Object>> list = schBaseUserService.getPushSchList(param);
			
			int length = list.size();
			if(length > 0){
				rows = length > rows ? rows : length;
				for (int i = 0; i < rows; i++) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("dataType", Constant.USER_TYPE_SCH);//数据类型，用于前台判断
					map.put("orgId", list.get(i).get("id"));//组织id
					map.put("name", list.get(i).get("school_short_name"));//名称
					map.put("logo", getLogo(list.get(i).get("school_logo")+""));//logo
					map.put("rowTwo", getSchoolTotal(list.get(i).get("student_total")+""));//学校毕业人数
					map.put("rowThree", getArea(list.get(i).get("location_code")+""));//对应企业所在地区
					resultList.add(map);
				}
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		}
	}
	
	/**
	 * 封装数据  学生
	 * @return
	 */
	public List<Map<String,Object>> getPushStuList(String orgId, String uid, String ids, int rows){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ids", ids);
			map.put("orgId", orgId);
			map.put(PAGE, DEFUALT_PAGE);
			map.put(PAGE_SIZE, rows);
			SchoolInfoLink link = linkService.selectByPrimaryKey(orgId);
			if(link != null){
				map.put("schoolCode", link.getSchoolCode());
			}
			List<Map<String,Object>> list = schBaseUserService.getPushStuList(map);
			
			map.clear();
			int length = list.size();
			if(length > 0){
				rows = length > rows ? rows : length;
				for (int i = 0; i < rows; i++) {
					Map<String,Object> mapVal = new HashMap<String,Object>();
					mapVal.put("dataType", Constant.USER_TYPE_STU);//数据类型，用于前台判断
					mapVal.put("orgId", list.get(i).get("id"));//组织id
					
					mapVal.put("name", StringUtil.getResumeName(list.get(i).get("surname")+"", list.get(i).get("miss_surname")+""));//名称
					mapVal.put("logo", getLogo(list.get(i).get("head_url")+""));//logo
					mapVal.put("rowTwo", list.get(i).get("school_name"));//学校
					mapVal.put("rowThree", getNameByCode(ConstantTables.COMM_MAJOR, 
							ConstantTables.MAJOR_COL_CODE, list.get(i).get("major_code")+"", 
							ConstantTables.MAJOR_COL_NAME));//专业名称
					resultList.add(mapVal);
				}
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
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
	
	public String getIds(String ids){
		String idsArray[] =  ids.split(",");
		String idsStr = "";
		for (String id : idsArray) {
			idsStr = "'" + idsStr + "','" + id;
		}
		idsStr = idsStr.substring(idsStr.indexOf(",", 1) +1, idsStr.length()) + "'";
		return idsStr;
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
	
	public  String getSchoolTotal(String total){
		String name = "";
		if(StringUtil.isNotEmpty(total)){
			name = "毕业生约" + total;
		}
		return name;
	}
	
	public String getSringStr(String s){
		String str = "";
		if(StringUtil.isNotEmpty(s)){
			str = s;
		}
		return str;
	}
	
	public String getEntName(String entSimpleName, String entName){
		if(StringUtil.isEmpty(entSimpleName)){
			entSimpleName = entName;
		}
		return entSimpleName;
	}
	
	
}
