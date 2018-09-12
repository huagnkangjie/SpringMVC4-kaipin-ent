package com.kaipin.university.presentation.action.basic;

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
import com.kaipin.university.model.user.SchoolInfo;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.SchoolUserInfo;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.ISchoolInfoService;
import com.kaipin.university.service.user.ISchoolUserInfoService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.StringUtil;

/**
 * 基本信息页面公用类
 * @author Mr-H
 *
 */
@RequestMapping("/basic")
@Controller
public class BasicAction extends BaseAction{
	
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	@Autowired
	private ISchoolInfoService schInfoService;
	@Autowired
	private ISchoolUserInfoService schUserInfoService;
	@Autowired
	private ISchBaseUserService schBaseUserInfoService;
	@Autowired
	private IBaseCodeService codeService;

	public static String BASIC = "/basic/basic";
	public static String BASIC_SHOW = "/basic/basic_show";
	
	
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request, Model model, User user){
		try {
			
			//初始化数据
			initPage(request, model, user);
			super.getLocationName();
			
			return BASIC;
		} catch (Exception e) {
			e.printStackTrace();
			return BASIC;
		}
		
	}
	
	/**
	 * 展示给其他用户的页面
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/show")
	public String show(HttpServletRequest request, Model model, User user){
		try {
			
			//初始化数据
			initPage(request, model, user);
			model.addAttribute("companyId", super.getOrgId(request, user));
			
			String cookie_own = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
			model.addAttribute("cookie_own", cookie_own);
			
			return BASIC_SHOW;
		} catch (Exception e) {
			e.printStackTrace();
			return BASIC_SHOW;
		}
		
	}
	
	public void initPage(HttpServletRequest request, Model model, User user){
		String cookie_sid = super.getOrgId(request, user);
		String cookie_uid = super.getUid(request, user);
		//获取基本信息 
		SchoolInfo info = schInfoService.selectByPrimaryKey(cookie_sid);
		SchoolInfoLink linkInfo = schLinkService.selectByPrimaryKey(cookie_sid);
		SchoolUserInfo userInfo = schUserInfoService.selectByPrimaryKey(cookie_uid);
		UserLocalauth baseUserInfo = localUserService.selectByPrimaryKey(cookie_uid);
		
		//判断是否含有头像
		String headUrl = Constant.VALUE_ZERO;
		if(linkInfo != null && StringUtil.isNotEmpty(linkInfo.getSchoolLogo())){
			headUrl = linkInfo.getSchoolLogo();
		}
		//背景
		String bg = Constant.VALUE_ZERO;
		if(linkInfo != null && StringUtil.isNotEmpty(linkInfo.getSchoolBg())){
			bg = linkInfo.getSchoolBg();
		}
		//判断是否审核通过
		String checkStatus = Constant.VALUE_ZERO;
		if(linkInfo != null && StringUtil.isNotEmpty(linkInfo.getVerifyStatus()+"")){
			checkStatus = linkInfo.getVerifyStatus()+"";
			if(checkStatus.equals(Constant.VALUE_ZERO)){
				//判断是否有资质认证
				List<Map<String,Object>> list = schBaseUserInfoService.selectDoc(cookie_uid);
				if(list.size() > 0){
					checkStatus = Constant.VALUE_THREE;
				}
			}
		}
		
		//院校特色
		String schoolFeature = Constant.VALUE_ZERO;
		if(linkInfo != null && StringUtil.isNotEmpty(linkInfo.getSchoolFeatureId()+"")){
			schoolFeature = getNameByCode(ConstantTables.SCHOOL_FEATURE, 
					ConstantTables.SCHOOL_FEATURE_CODE, linkInfo.getSchoolFeatureId()+"", 
					ConstantTables.SCHOOL_FEATURE_NAME);
			
		}
		
		//院校类别
		String schoolClass = Constant.VALUE_ZERO;
		if(linkInfo != null && StringUtil.isNotEmpty(linkInfo.getSchoolClassId()+"")){
			schoolClass = getNameByCode(ConstantTables.SCHOOL_CLASS, 
					ConstantTables.SCHOOL_CLASS_CODE, linkInfo.getSchoolClassId()+"", 
					ConstantTables.SCHOOL_CLASS_NAME);
			
		}
		
		//院校人数
		String studentCountName = Constant.VALUE_ZERO;
		String studentCount = linkInfo.getStudentCount();
		String studentTotal = linkInfo.getStudentTotal();
		if(StringUtil.isNotEmpty(studentCount)){
			studentCount = "在校学生" + studentCount + "+";
		}
		if(StringUtil.isNotEmpty(studentTotal)){
			studentTotal = "历届学生" + studentTotal + "+";
		}
		if(StringUtil.isNotEmpty(studentCount) && StringUtil.isNotEmpty(studentTotal)){
			studentCountName = studentCount + "/" + studentTotal;
		}else if(StringUtil.isNotEmpty(studentCount) && StringUtil.isEmpty(studentTotal)){
			studentCountName = studentCount;
		}else if(StringUtil.isEmpty(studentCount) && StringUtil.isNotEmpty(studentTotal)){
			studentCountName = studentTotal;
		}else{
			studentCountName = Constant.VALUE_ZERO;
		}
		
		//网址
		String website = "";
		if(StringUtil.isNotEmpty(linkInfo.getWebsite())){
			website = linkInfo.getWebsite();
			website = website.replace("https://", "");
			website = website.replace("http://", "");
		}
		
		//院校介绍
		String detail = schLinkService.getLinkDetail(cookie_sid);
		
		//所属地区
		String locationCode = linkInfo.getLocationCode();
		String provinceCode = this.provinceCode(locationCode);//省
		String cityCode = this.cityCode(locationCode);//市
		String countyCode = locationCode;//县
		
		List<Map<String,Object>> provinceList = codeService.getLocationListExceptOwn("489");
		List<Map<String,Object>> cityList = new ArrayList<>();
		List<Map<String,Object>> countyList = new ArrayList<>();
		if(!codeService.getParentCode(locationCode).equals("489")){
			 cityList = codeService.getLocationListExceptOwn(provinceCode);
			 countyList = codeService.getLocationListExceptOwn(cityCode);
		}
		
//		if(cityCode.equals(countyCode)){
//			 cityList = countyList;
//			 countyList = null;
//			 countyCode = "";
//		}
		
		model.addAttribute("website", website);//网址
		model.addAttribute("schoolClass", schoolClass);//院校分类
		model.addAttribute("detail", detail);//院校详情
		model.addAttribute("studentCount", studentCount);//院校人数，只显示现在人数
		model.addAttribute("studentCountName", studentCountName);//院校人数
		model.addAttribute("schoolFeature", schoolFeature);//院校类型
		model.addAttribute("checkStatus", checkStatus);
		model.addAttribute("headUrl", headUrl);
		model.addAttribute("bg", bg);
		model.addAttribute("info", info);
		model.addAttribute("linkInfo", linkInfo);
		model.addAttribute("baseUserInfo", baseUserInfo);
		model.addAttribute("userInfo", userInfo);
		
		//所属地区
		model.addAttribute("locationCode", locationCode);
		model.addAttribute("provinceCode", provinceCode);
		model.addAttribute("cityCode", cityCode);
		model.addAttribute("countyCode", countyCode);
		
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("cityList", cityList);
		model.addAttribute("countyList", countyList);
		
		model.addAttribute("orgId", cookie_sid);
		model.addAttribute("uid", cookie_uid);
		
		this.setSysAttr(model, info.getSchoolName(), null, null);
	}
	
	/**
	 * 维护基本信息
	 * @param request
	 * @param oper
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(HttpServletRequest request, String oper, SchoolInfoLink link, String detail){
		Json json = new Json();
		try {
			
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
			if(StringUtil.isNotEmpty(detail)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", cookie_sid);
				map.put("detail", detail);
				List<Map<String,Object>> list = schLinkService.getLinkDetailList(cookie_sid);
				if(list.size() > 0){
					schLinkService.updateDetail(map);
				}else{
					schLinkService.insertDetail(map);
				}
			}
			
			link.setId(cookie_sid);
			if(oper.equals("basic_logo") || oper.equals("info")){
				schLinkService.updateByPrimaryKeySelective(link);
			}
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 详情
	 * @param request
	 * @param oper
	 * @param link
	 * @return
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public Json detail(HttpServletRequest request, String oper){
		Json json = new Json();
		try {
			
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			String u_type = CookieUtil.getCookieInfoByKey(request, Constant.USER_TYPE);
			
			SchoolInfoLink link = new SchoolInfoLink();
			String detail = "";
			String provinceCode = "";
			String cityCode = "";
			String countyCode = "";
			
			if(StringUtil.isNotEmpty(cookie_sid)){
				link = schLinkService.selectByPrimaryKey(cookie_sid);
				detail = schLinkService.getLinkDetail(cookie_sid);
				if(link != null){
					//所属地区
					String locationCode = link.getLocationCode();
					provinceCode = this.provinceCode(locationCode);//省
					cityCode = this.cityCode(locationCode);//市
					countyCode = locationCode;//县
					if(countyCode.equals(cityCode)){
						countyCode = "";
					}
				}
//				List<Map<String,Object>> provinceList = codeService.getLocationListExceptOwn("489");
//				List<Map<String,Object>> cityList = codeService.getLocationListContainOwn(provinceCode);
//				List<Map<String,Object>> countyList = codeService.getLocationListContainOwn(cityCode);
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("info", link);
			map.put("org_id", cookie_sid);
			map.put("u_id", cookie_uid);
			map.put("provinceCode", provinceCode);
			map.put("cityCode", cityCode);
			map.put("countyCode", countyCode);
			
			map.put("u_type", u_type);
			
			
			json.setObj(map);
			json.setMsg(detail);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
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
	
	/**
	 * 判断是否有网址
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/getWebsite")
	@ResponseBody
	public Json demo1(HttpServletRequest request, User user){
		Json json = new Json();
		try {
			String cookie_sid = super.getOrgId(request, user);
			SchoolInfoLink link = schLinkService.selectByPrimaryKey(cookie_sid);
			if(StringUtil.isNotEmpty(link.getWebsite())){
				json.setObj(link.getWebsite());
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
}
