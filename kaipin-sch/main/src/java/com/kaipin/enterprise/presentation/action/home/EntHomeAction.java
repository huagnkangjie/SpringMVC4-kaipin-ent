package com.kaipin.enterprise.presentation.action.home;

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

import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.repository.dao.user.ICompanyUserInfoDao;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.student.service.resume.IResumeService;
import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.ConstantTables;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.Page;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.sun.accessibility.internal.resources.accessibility;

/**
 * 企业主页
 * @author Mr-H
 *
 */
@RequestMapping("")
@Controller
public class EntHomeAction extends BaseAction{
	
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private ICompanyUserInfoDao companyUserService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private IBaseCodeService codeService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	@Autowired
	private IResumeService resumeService;

	/**
	 * 企业主页
	 * @param request
	 * @return
	 */
	@RequestMapping("/ent/home")
	public String homePage(HttpServletRequest request, Model model, User user, String userId){
		CompanyInfo info = companyInfoService.selectByPrimaryKey(this.getOrgId(request, user));
		String orgName = "企业";
		if(info != null){
			orgName = info.getEntName();
		}
		
		getDetial(request, model, user);
		
		model.addAttribute("index", "index");
		this.setSysAttr(model, orgName, null, null);
		return "/ent/home/ent_home";
	}
	
	
	/**
	 * 企业展示页面
	 * @return
	 */
	@RequestMapping("/show")
	public String show(HttpServletRequest request, Model mode, User user){
		try {
			getDetial(request, mode, user);
			
			return "/ent/basic/basic_show";
		} catch (Exception e) {
			e.printStackTrace();
			return ERRO;
		}
	}
	/**
	 * 企业基本信息页面 -- 用于自己维护
	 * @return
	 */
	@RequestMapping("/basic")
	public String basic(HttpServletRequest request, Model mode, User user){
		try {
			getDetial(request, mode, user);
			
			return "/ent/basic/basic";
		} catch (Exception e) {
			e.printStackTrace();
			return ERRO;
		}
	}
	
	public void getDetial(HttpServletRequest request, Model mode, User user){
		try {
			String companyId = super.getOrgId(request, user);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(companyId);
			
			//企业简称
			String simpleEntName = info.getEntSimpleName();
			if(StringUtil.isEmpty(simpleEntName)){
				simpleEntName = info.getEntName();
			}
			
			//logo
			String logo = info.getLogo();
			if(StringUtil.isEmpty(logo)){
				logo = Constant.VALUE_ZERO;
			}
			//bg
			String bg = info.getBgUrl();
			if(StringUtil.isEmpty(bg)){
				bg = Constant.VALUE_ZERO;
			}
			
			//所属行业
			String industryType = info.getIndustryCode();
			if(StringUtil.isNotEmpty(industryType)){
				industryType = codeService.getNameByCode(ConstantTables.COMM_INDUSTRY, 
						ConstantTables.INDUSTRY_COL_CODE, industryType, 
						ConstantTables.INDUSTRY_COL_NAME);
			}
			
			String peopleCount = info.getPeopleNumber()+"";
			if(StringUtil.isEmpty(peopleCount)){
				peopleCount = Constant.VALUE_ZERO;
			}
			
			//公司类型
			String companyType = info.getCompanyTypeCode();
			if(StringUtil.isNotEmpty(companyType)){
				companyType = codeService.getNameByCode(ConstantTables.COMM_ENT_TYPE, 
						ConstantTables.ENT_TYPE_COL_CODE, companyType, 
						ConstantTables.ENT_TYPE_COL_NAME);
			}
			//所在区域--中文名
			String officeArea = info.getOfficeArea();
			if(StringUtil.isNotEmpty(officeArea)){
				officeArea = codeService.getAreaAllNameByCode(officeArea);
			}
			//所在区域--列表
			//所属地区
			String locationCode = info.getOfficeArea();
			String provinceCode = this.provinceCode(locationCode);//省
			String cityCode = this.cityCode(locationCode);//市
			String countyCode = locationCode;//县
			
			List<Map<String,Object>> provinceList = codeService.getLocationListExceptOwn("489");
			List<Map<String,Object>> cityList = new ArrayList<>();
			List<Map<String,Object>> countyList = new ArrayList<>();
			if(!codeService.getParentCode(locationCode).equals("489")){
				 cityList = codeService.getLocationListContainOwn(provinceCode);
				 countyList = codeService.getLocationListContainOwn(countyCode);
			}
			
			if(cityCode.equals(countyCode)){
				 cityList = countyList;
				 countyList = null;
				 countyCode = "";
			}
			
			
			
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			String cookie_orgId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			UserLocalauth localUser = localUserService.selectByPrimaryKey(cookie_uid);
			String utype = localUser.getCategoryId();
			String headUrl = Constant.VALUE_ZERO;
			String orgName = "";
			if(utype.equals(Constant.USER_TYPE_ENT)){
				headUrl = logo;
				orgName = info.getEntSimpleName();
			}else if(utype.equals(Constant.USER_TYPE_STU)){
				//headUrl = logo;
			}else if(utype.equals(Constant.USER_TYPE_SCH)){
				SchoolInfoLink link = schLinkService.selectByPrimaryKey(cookie_orgId);
				headUrl = link.getSchoolLogo();
				orgName = link.getSchoolShortName();
			}
			
			//网址
			String website = "";
			if(StringUtil.isNotEmpty(info.getWebsite())){
				website = info.getWebsite();
				website = website.replace("https://", "");
				website = website.replace("http://", "");
			}
			
			mode.addAttribute("simpleEntName", simpleEntName);//企业简称
			mode.addAttribute("website", website);//网址
			mode.addAttribute("logo", logo);//企业logo
			mode.addAttribute("bg", bg);
			mode.addAttribute("industryType", industryType);
			mode.addAttribute("companyType", companyType);
			mode.addAttribute("officeArea", officeArea);
			mode.addAttribute("peopleCount", peopleCount);
			mode.addAttribute("infos", info);
//			
			mode.addAttribute("headUrl", headUrl);
			mode.addAttribute("orgName", orgName);
			
			mode.addAttribute("companyId", companyId);//当前查询页面的组织id
			mode.addAttribute("userId", cookie_uid);//当前查询页面的组织id
			mode.addAttribute("cookie_companyId", companyId);//当前查询页面的组织id
			
			mode.addAttribute("verify_status", info.getVerifyStatus());//资质验证状态
			
			
			//所属地区 --列表所用
			mode.addAttribute("locationCode", info.getOfficeArea());
			mode.addAttribute("provinceCode", provinceCode);
			mode.addAttribute("cityCode", cityCode);
			mode.addAttribute("countyCode", countyCode);
			
			mode.addAttribute("provinceList", provinceList);
			mode.addAttribute("cityList", cityList);
			mode.addAttribute("countyList", countyList);
			
			this.setSysAttr(mode, info.getEntName(), null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 获取最新5条信息简历信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getResume")
	@ResponseBody
	public Json getResume(HttpServletRequest request, Page page){
		try {
			Json json = new Json();
			page.setRows(5);
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("today", TimeUtil.currentTimeMillis());
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = resumeService.getAll(map);
			List<Map<String,Object>> listCounts = resumeService.getIndexResumeListCounts(map);
			List<Map<String,Object>> listResult = new ArrayList<>();
			String counts = "0";
			map.clear();
			if(list.size() > 0){
				json.setSuccess(true);
				String head_url = "";
				for (Map<String, Object> map2 : list) {
					head_url = map2.get("head_url")+"";
					if(StringUtil.isNotEmpty(head_url)){
						if(!head_url.startsWith("http")){
							PropUtil pro = new PropUtil();
							head_url = pro.getValue(Constant.STU_HEAD_URL) + head_url;
						}
					}
					map2.put("head_url", head_url);
					listResult.add(map2);
				}
				map.put("list", listResult);
				json.setObj(map);
				if(listCounts.size() > 0){
					counts = String.valueOf(listCounts.get(0).get(Constant.COUNT));
					json.setSuccess(true);
					map.put("counts", counts);
					json.setObj(map);
				}
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
