package com.kaipin.oss.presentation.action.position;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kaipin.oss.common.page.GenericDefaultPage;
import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.common.web.CookieUtils;
import com.kaipin.oss.constant.Constant;
import com.kaipin.oss.model.common.Code;
import com.kaipin.oss.model.common.CommCompanyType;
import com.kaipin.oss.model.common.CommEducation;
import com.kaipin.oss.model.common.CommJobType;
import com.kaipin.oss.model.common.CommMajor;
import com.kaipin.oss.model.common.CommWorkExperience;
import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.model.position.PositionInfo;
import com.kaipin.oss.presentation.action.Constants;
import com.kaipin.oss.service.common.CommonCodeService;
import com.kaipin.oss.service.company.CompanyInfoService;
import com.kaipin.oss.service.position.PositionInfoService;
import com.kaipin.oss.util.StringUtil;
import com.kaipin.oss.util.TimeUtil;

@Controller
@RequestMapping("/management/position")
public class PositionAction {

	public static String LIST = "position/list";//企业职位列表
	public static String DETAIL = "position/detail";//职位详情
	
	@Autowired
	private PositionInfoService positionInfoService;
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private CompanyInfoService companyInfoService;
	
	/**
	 *	根据企业id查找职位列表
	 *  @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest   request, Map<String, Object> map, 
			Integer pageNo , ModelMap model, CompanyInfo companyInfo, CompanyUserInfo userInfo,
			PositionInfo position, String startTime, String endTime, String companyId) {

		try {
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("companyId", companyId);
			param.put("today", "0");
			
			int pageSize=	CookieUtils
					.getPageSize(request);
			
			IGenericPage<PositionInfo> pages = 
					positionInfoService.getPositionList(param, GenericDefaultPage.cpn(pageNo),
					pageSize);
			model.addAttribute(Constants.PAGE, pages);
			model.addAttribute("companyId", companyId);
			return LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return LIST;
		}
	}
	
	
	/**
	 * 职位详情
	 * @return
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest  request, ModelMap model, String positionId,  String companyId){
		try {
			getDetail(request, model, positionId, companyId);
			return DETAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return DETAIL;
		}
	}
	
	public void getDetail(HttpServletRequest  request, ModelMap model, String positionId,  String companyId){
		model.addAttribute("positionId", positionId);
		
		PositionInfo function =	positionInfoService.selectByPrimaryKey(positionId);
		
		//顶部时间
		Long timeUpdate = function.getLastUpdatedTime();
		Long time = null;
		if(timeUpdate == null){
			time = function.getCreateTime();
		}else{
			time = timeUpdate;
		}
		String times = TimeUtil.getTimeByMillis(time);
		String mouth = times.substring(5, 7);
		mouth = Integer.valueOf(mouth) >= 10 ? mouth : mouth.substring(1, 2);
		times = times.substring(0, 4) + "年" + mouth + "月" + times.substring(8, 10) + "日";
		//function.setCreateTime(time);
		model.addAttribute("createTime", times);
		//工作地区
		String workArea = function.getLocationCode();
		String parentCode = "";
		if(StringUtil.isNotEmpty(workArea)){
			workArea = commonCodeService.getLocationByCodeService(workArea);
			function.setLocationCode(workArea);
		}
		//获取行业
		String industryType = function.getIndustryCode();
		if(StringUtil.isNotEmpty(industryType)){
			Code codeObje = commonCodeService.getCommIndustry(industryType);
			if(codeObje != null && codeObje.getCommIndustry() != null){
				industryType = codeObje.getCommIndustry().getIndustryName();
			}
			function.setIndustryCode(industryType);
		}
		//获取教育
		String edu = function.getEducationCode();
		if(StringUtil.isNotEmpty(edu)){
			CommEducation o  = commonCodeService.getEducation(edu);
			if(o != null){
				edu = o.getEducationName();
			}
			function.setEducationCode(edu);
		}
		//职能
		String jobType = function.getJobTypeCode();
		if(StringUtil.isNotEmpty(jobType)){
			CommJobType o = commonCodeService.getJobType(jobType);
			if(o != null){
				jobType = o.getJobTypeName();
			}
			function.setJobTypeCode(jobType);
		}
		
		//工作经验
		String workExp = function.getWorkExperienceCode();
		if(StringUtil.isNotEmpty(workExp)){
			CommWorkExperience o = commonCodeService.getWorkExperience(workExp);
			if(o != null){
				workExp = o.getWorkExperienceName();
			}
			function.setWorkExperienceCode(workExp);
		}else{
			function.setWorkExperienceCode("");
		}
		
		//薪资
		int salaryType = function.getSalaryType();
		String salaryTypeVal = "面议";
		int salaryStart = 0;
		int salaryEnd = 0;
		
		if(function.getSalaryEnd() != null){
			salaryEnd = function.getSalaryEnd();
		}
		
		if(function.getSalaryStart() != null){
			salaryStart = function.getSalaryStart();
		}
		
		if(salaryType == Constant.OPER_ZERO){
			salaryTypeVal = "面议";
		}else if(salaryType == Constant.OPER_ONE){
			//if(StringUtil.isNotEmpty(salaryStart) && StringUtil.isNotEmpty(salaryEnd)){
				salaryTypeVal = "月薪    " + salaryStart + " - " + salaryEnd + " 元";
			//}else {
				//salaryTypeVal = "月薪";
			//}
		}else if(salaryType == Constant.OPER_TWO){
			//if(StringUtil.isNotEmpty(salaryStart) && StringUtil.isNotEmpty(salaryEnd)){
				salaryTypeVal = "年薪    " + salaryStart + " - " + salaryEnd + " 元";
			//}else {
			//	salaryTypeVal = "年薪";
			//}
		}
		model.addAttribute("salaryTypeVal", salaryTypeVal);
		
		//性别
		String sex = function.getSexCode();
		if(StringUtil.isNotEmpty(sex)){
			if(sex.equals(Constant.VALUE_ZERO)){
				sex = "不限";
			}else if(sex.equals(Constant.VALUE_ONE)){
				sex = "女";
			}else if(sex.equals(Constant.VALUE_TWO)){
				sex = "男";
			}else{
				sex = "不限";
			}
			model.addAttribute("sexVal", sex);
			function.setSexCode(sex);
		}
		
		//有效期
		String endTime = function.getEndTime();
		if(StringUtil.isNotEmpty(endTime)){
			endTime = TimeUtil.getTimeByMillis(Long.valueOf(endTime))
					.substring(0, 10);
			String mouths = TimeUtil.getMouthsBetweenMouth(
					TimeUtil.getTimeByMillis(function.getCreateTime()), 
					endTime);
			if(Integer.valueOf(mouths) > 12){
				endTime = "长期有效";
			}else{
				endTime = mouths + "个月";
			}
		}else{
			endTime = "长期有效";
		}
		function.setEndTime(endTime);
		
		//年薪福利
		String salaryYear = function.getSalaryYear();
		if(StringUtil.isNotEmpty(salaryYear)){
			if(salaryYear.equals(Constant.VALUE_ZERO)){
				salaryYear = "无";
			}else{
				if(salaryYear.equals("24")){
					salaryYear = salaryYear + "个月以上";
				}else{
					salaryYear = salaryYear + "个月";
				}
				
			}
		}else{
			salaryYear = "";
		}
		model.addAttribute("salaryYearVal", salaryYear);
		function.setSalaryYear(salaryYear);
		
		//专业要求
		String majroRequest = function.getMajorRequest();
		if(StringUtil.isNotEmpty(majroRequest)){
			String ids[] = majroRequest.split(",");
			String name = "";
			for (int i = 0; i < ids.length; i++) {
				String majarName = "";
				CommMajor o = commonCodeService.getMajor(ids[i]);
				if(o != null){
					majarName = o.getMajorName();
				}
				name = name + " " + majarName;
			}
			function.setMajorRequest(name);
		}
		
		//下属人数
		Object o = function.getDepartmentNumbers();
		String departmentNumbers = "";
		if(o != null){
			departmentNumbers = String.valueOf(o);
		}
		model.addAttribute("departmentNumbers", departmentNumbers);
		
		//企业性质
		CompanyInfo info = companyInfoService.selectByPrimaryKey(companyId);
		String companyType = info.getCompanyTypeCode();
		if(StringUtil.isEmpty(companyType)){
			companyType = "无";
		}else{
			CommCompanyType oo = commonCodeService.getCompanyType(companyType);
			if(oo != null){
				companyType = oo.getCompanyTypeName();
			}
		}
		
		//企业规模
		Object o2 = info.getPeopleNumber();
		String peopleNumber = "";
		if(o2 != null){
			peopleNumber = String.valueOf(o2);
		}
		
		//公司所在地区
		String companyArea = "";
		if(StringUtil.isNotEmpty(info.getOfficeArea())){
			companyArea = commonCodeService.getLocationByCodeService(info.getOfficeArea());
		}
		
		//公司所属行业
		String Industry = info.getIndustryCode();
		if(StringUtil.isNotEmpty(Industry)){
			Code oc = commonCodeService.getCommIndustry(Industry);
			if(oc != null && oc.getCommIndustry() != null){
				Industry = oc.getCommIndustry().getIndustryName();
			}
		}
		
		model.addAttribute("peopleNumbers", peopleNumber);
		
		model.addAttribute("logo", info.getLogo());
		model.addAttribute("basic", info);
		model.addAttribute("companyType", companyType);
		model.addAttribute("Industry", Industry);
		model.addAttribute("companyArea", companyArea);
		
		model.addAttribute("positionInfo", function);
	}
	
}
