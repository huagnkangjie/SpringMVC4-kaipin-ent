package com.kaipin.common.presentation.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.User;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.student.model.user.StuUser;
import com.kaipin.student.service.user.IStuUserService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.ISchoolInfoService;

/**
 * 基础类
 * @author Mr-H
 *
 */
public class BaseAction {
	
	@Autowired
	private IBaseCodeService service;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private ISchoolInfoService schoolInfoService;
	@Autowired
	private ISchoolInfoLinkService linkService;
	@Autowired
	private IStuUserService stuUserService;
	
	public static final String ERRO = "/error/404";
	
	/**
	 * 返回组织id
	 * @param request
	 * @param user
	 * @return
	 */
	public String getOrgId(HttpServletRequest request, User user){
		if(StringUtil.isEmpty(user.getOrg_id())){
			user.setOrg_id(CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID));
		}
		return user.getOrg_id();
	}
	
	/**
	 * 返回用户id
	 * @param request
	 * @param user
	 * @return
	 */
	public String getUid(HttpServletRequest request, User user){
		if(StringUtil.isEmpty(user.getU_id())){
			user.setU_id(CookieUtil.getCookieInfoByKey(request, Constant.USER_UID));
		}
		return user.getU_id();
	}
	
	public String getOrgName(HttpServletRequest request, User user){
		String orgId = getOrgId(request, user);
		String uType = CookieUtil.getCookieInfoByKey(request, Constant.USER_TYPE);
		String orgNames = "";
		try {
			if(StringUtil.isNotEmpty(orgId) && StringUtil.isNotEmpty(uType)){
				if(uType.equals(Constant.USER_TYPE_ENT)){
					orgNames = companyInfoService.selectByPrimaryKey(orgId).getEntName();
				}else if(uType.equals(Constant.USER_TYPE_SCH)){
					orgNames = schoolInfoService.selectByPrimaryKey(orgId).getSchoolName();
				}else if(uType.equals(Constant.USER_TYPE_STU)){
					StuUser stu =  stuUserService.selectByPrimaryKey(orgId);
					orgNames = stu.getSurname() + stu.getMissSurname();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orgNames;
	}


	public void getLocationName(){
		service.getAreaAllNameByCode("530");
	}
	
	/**
	 * 设置系统参数
	 * @param model
	 * @param title
	 * @param keywords
	 * @param discription
	 */
	public void setSysAttr(Model model, String title, String keywords, String description){
		
		PropUtil util = getPropUtil();
		
		if(StringUtil.isEmpty(title)){
			title = getProValue(util, Constant.SYS_TITLE);
		}else{
			title = title + " - " + getProValue(util, Constant.SYS_TITLE);
		}
		
		if(StringUtil.isEmpty(keywords)){
			keywords = getProValue(util, Constant.SYS_KEYWORDS);
		}
		
		if(StringUtil.isEmpty(description)){
			description = getProValue(util, Constant.SYS_DESCRIPTION);
		}
		
		model.addAttribute("sys_title", title);
		model.addAttribute("sys_keywords", keywords);
		model.addAttribute("sys_description", description);
	}
	
	public PropUtil getPropUtil(){
		return new PropUtil(Constant.PRO_FILE_CONSTANTS);
	}
	
	public String getProValue(PropUtil util, String key){
		return util.getValue(key);
	}
	
	/**
	 * 获取省code
	 * @param locationCode
	 * @return
	 */
	public String provinceCode(String locationCode){
		String parentCode = service.getParentCode(locationCode);
		if(parentCode.equals("530") //北京
				|| parentCode.equals("538")//上海
				|| parentCode.equals("551")//重庆
				|| parentCode.equals("531")){//天津
		}else{
			parentCode = service.getProvinceCode(locationCode);
		}
		
		return parentCode;
	}
	
	/**
	 * 获取市code
	 * @param locationCode
	 * @return
	 */
	public String cityCode(String locationCode){
		//判断是否是二级城市， 如：成都
		String parentCode = service.getParentCode(locationCode);
		if(!parentCode.equals("489")){
			parentCode = service.getParentCode(parentCode);
			if(!parentCode.equals("489")){//是三级城市
				parentCode = service.getCityCode(locationCode);
			}else{
				parentCode = locationCode;
			}
		}
		return parentCode;
	}
	
	/**
	 * 获取 区/县 code
	 * @param locationCode
	 * @return
	 */
	public String countyCode(String locationCode){
		String parentCode = service.getParentCode(locationCode);
		if(parentCode.equals("530") //北京
				|| parentCode.equals("538")//上海
				|| parentCode.equals("551")//重庆
				|| parentCode.equals("531")
				|| parentCode.equals("489")){//天津
		}else{
			parentCode = service.getCountyCode(locationCode);
		}
		
		return parentCode;
	}
	
	
	
}
