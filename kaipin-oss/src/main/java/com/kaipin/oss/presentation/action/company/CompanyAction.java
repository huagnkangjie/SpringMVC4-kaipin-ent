package com.kaipin.oss.presentation.action.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.oss.common.page.GenericDefaultPage;
import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.common.pojo.Json;
import com.kaipin.oss.common.pojo.StautsBean;
import com.kaipin.oss.common.web.CookieUtils;
import com.kaipin.oss.constant.AppSearchConstant;
import com.kaipin.oss.constant.Constant;
import com.kaipin.oss.model.common.Code;
import com.kaipin.oss.model.company.CompanyDocument;
import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.model.company.CompanyInfoFeedback;
import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.service.common.CommonCodeService;
import com.kaipin.oss.service.company.CompanyInfoBaseService;
import com.kaipin.oss.service.company.CompanyInfoFeedbackService;
import com.kaipin.oss.service.company.CompanyInfoService;
import com.kaipin.oss.service.company.CompanyUserInfoService;
import com.kaipin.oss.util.HttpPostUtil;
import com.kaipin.oss.util.JsonUtil;
import com.kaipin.oss.util.LuceneUtil;
import com.kaipin.oss.util.PropUtil;
import com.kaipin.oss.util.StringUtil;
import com.kaipin.oss.util.TimeUtil;
import com.kaipin.oss.util.UuidUtil;

import sun.swing.StringUIClientPropertyKey;

import com.kaipin.oss.presentation.action.Constants;
import com.kaipin.oss.security.SecurityUtils;
import com.kaipin.oss.security.ShiroUser;
/**
 * 企业类
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/management/company/main")
public class CompanyAction {

	private final String LIST = "company/list";//企业管理列表
	private final String ADD = "company/add";//新增页面
	private final String DETAIL = "company/detail";//企业详情
	private final String ZZSH_LISt = "company/zzsh_list";//企业资质审核
	private final String ZZXQ = "company/zzxq";//企业资质详情

	@Autowired
	private CompanyInfoBaseService companyBaseService;//企业基础
	@Autowired
	private CompanyInfoService companyInfoService;//企业基本信息
	@Autowired
	private CompanyUserInfoService companyUserService;//企业用户
	@Autowired
	private CompanyInfoFeedbackService feedbackService;//反馈
	@Autowired
	private CommonCodeService commonCodeService;

	/**
	 * 获取企业list
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest   request, Map<String, Object> map, 
			Integer pageNo , ModelMap model, CompanyInfo companyInfo, CompanyUserInfo userInfo,
			String startTime, String endTime) {

		try {
			
			Map<String,Object> param = new HashMap<String,Object>();
			
			if(companyInfo != null){
				param.put("entName", companyInfo.getEntName());
			}
			
			if(userInfo != null){
				param.put("isCheckMail", userInfo.getIsCheckMail());
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
			
			@SuppressWarnings("unchecked")
			IGenericPage<CompanyInfoCount> pages = 
					companyBaseService.getPage(param, GenericDefaultPage.cpn(pageNo),
					pageSize);

			model.addAttribute(Constants.PAGE, pages);
			model.addAttribute("companyInfo", companyInfo);
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("isCheckMail", userInfo.getIsCheckMail());
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			return LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return LIST;
		}
		
	}
	
	/**
	 * 首页获直播点播统计数据取统计数据
	 * @param companyId
	 * @return
	 */
	@RequestMapping("/livecounts")
	@ResponseBody
	public Json getLiveCounts(String companyId){
		Json json = new Json();
		try {
			List<String> list = getLiveCount(companyId);
			json.setObj(list);
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 判断该企业是否配置邮箱
	 * @param companyId
	 * @return
	 */
	@RequestMapping("/offerconfig")
	@ResponseBody
	public Json checkOfferConfig(String companyId){
		Json json = new Json();
		try {
			List<Map<String, Object>> list = companyBaseService.checkOfferConfig(companyId);
			String oper = "否";
			if(list.size() > 0){
				oper = "是";
			}
			json.setObj(oper);
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	
	/**
	 * 获取企业基本信息
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String detail(HttpServletRequest   request, String companyId, ModelMap model,
			String resumeCount, String positionCount, String followCount, String userId) {

		try {
			
			if(StringUtil.isNotEmpty(companyId)){
				
				if(StringUtil.isEmpty(resumeCount)){
					resumeCount = Constant.VALUE_ZERO;
				}
				if(StringUtil.isEmpty(positionCount)){
					positionCount = Constant.VALUE_ZERO;
				}
				if(StringUtil.isEmpty(followCount)){
					followCount = Constant.VALUE_ZERO;
				}
				
				CompanyInfo info = companyInfoService.selectByPrimaryKey(companyId);
				CompanyUserInfo user = companyUserService.selectByPrimaryKey(userId);
				
				if(info != null){
					if(StringUtil.isNotEmpty(info.getCompanyTypeCode())){
						Code comm = commonCodeService.getCommIndustry(info.getIndustryCode());
						if(comm.getCommIndustry() != null){
							info.setCompanyTypeCode(comm.getCommIndustry().getIndustryName());
						}
					}
				}
				
				//获取宣讲会统计
				List<String> liveCountList = getLiveCount(companyId);
				
				model.addAttribute("xjhYg", liveCountList.get(0));
				model.addAttribute("xjhZb", liveCountList.get(1));
				model.addAttribute("xjhDb", liveCountList.get(2));
				
				//获取offer发送量
				String offer = Constant.VALUE_ZERO;
				List<Map<String,Object>> offerList = companyBaseService.getOfferCount(companyId);
				if(offerList.size() > 0){
					offer = String.valueOf(offerList.get(0).get("count"));
				}
				
				model.addAttribute("offer", offer);
				
				//企业基本信息
				model.addAttribute("basicInfo", info);
				model.addAttribute("resumeCount", resumeCount);
				model.addAttribute("positionCount", positionCount);
				model.addAttribute("followCount", followCount);
				model.addAttribute("user", user);
				model.addAttribute("companyId", companyId);
			}
			return DETAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return DETAIL;
		}
		
	}
	
	public List<String> getLiveCount(String companyId){
		List<String> list = new ArrayList<String>();
		String xjhYg = Constant.VALUE_ZERO,xjhZb = Constant.VALUE_ZERO,xjhDb = Constant.VALUE_ZERO;
		list.add(xjhYg);
		list.add(xjhZb);
		list.add(xjhDb);
		try {
			list.clear();
			List<Map<String,Object>> xjhList = companyBaseService.getXJHCount(companyId);
			if(xjhList.size() > 0){
				for (int i = 0; i < xjhList.size(); i++) {
					if(String.valueOf(xjhList.get(i).get("type")).equals(Constant.VALUE_ONE)){
						xjhYg = String.valueOf(xjhList.get(i).get(Constant.COUNT));
					}else if(String.valueOf(xjhList.get(i).get("type")).equals(Constant.VALUE_TWO)){
						xjhZb = String.valueOf(xjhList.get(i).get(Constant.COUNT));
					}else if(String.valueOf(xjhList.get(i).get("type")).equals(Constant.VALUE_THREE)){
						xjhDb = String.valueOf(xjhList.get(i).get(Constant.COUNT));
					}
				}
			}
			list.add(xjhYg);
			list.add(xjhZb);
			list.add(xjhDb);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		
	}
	
	
	/**
	 * 资质审核列表
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/zzlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String zzlist(HttpServletRequest   request, CompanyInfo companyInfo, 
			CompanyUserInfo userInfo, String startTime, String endTime, 
			Integer pageNo , ModelMap model) {

		try {
			
			Map<String,Object> param = new HashMap<String,Object>();
			
			if(companyInfo != null){
				param.put("entName", companyInfo.getEntName());
				param.put("verifyStatus", companyInfo.getVerifyStatus());
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
			
			IGenericPage<CompanyInfoCount> pages = 
					companyBaseService.getCompanyDocList(param, GenericDefaultPage.cpn(pageNo),
							pageSize);

			model.addAttribute(Constants.PAGE, pages);
			model.addAttribute("companyInfo", companyInfo);
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("verifyStatus", companyInfo.getVerifyStatus());
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			return ZZSH_LISt;
		} catch (Exception e) {
			e.printStackTrace();
			return ZZSH_LISt;
		}
		
	}
	
	/**
	 * 资质详情
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/zzxq", method = { RequestMethod.GET, RequestMethod.POST })
	public String zzxq(HttpServletRequest   request, String companyId, ModelMap model) {
		try {
			String businessLicensePath ="";
			String idCardPath ="";
			String employeeCard ="";
			String businessCard ="";
			String entName = "";
			if(StringUtil.isNotEmpty(companyId)){
				List<CompanyDocument> doc = companyBaseService.getComanyDoc(companyId);
				if(doc.size() > 0){
					for (int i = 0; i < doc.size(); i++) {
						String type = doc.get(i).getDocumentType();
						if(type.equals("business_license")){//营业执照
							businessLicensePath = doc.get(i).getDocumentPath();
						}else if(type.equals("id_card")){//身份证
							idCardPath = doc.get(i).getDocumentPath();
						}else if(type.equals("employee_card")){//雇佣证明
							employeeCard = doc.get(i).getDocumentPath();
						}else if(type.equals("business_card")){//名片
							businessCard = doc.get(i).getDocumentPath();
						}
						
					}
				}
				
				
				CompanyInfo info = companyInfoService.selectByPrimaryKey(companyId);
				if(info != null){
					entName = info.getEntName();
				}
				
			}
			//模板id
			String configIds = "";
			//获取配置list
			List<CompanyInfoFeedback> configList = feedbackService.getClassConfig();
			List<CompanyInfoFeedback> templeteList = new ArrayList<CompanyInfoFeedback>();
			//获取模板list
			String configId = "";
			if(configList.size() > 0){
				configId = "";
				for (int i = 0; i < configList.size(); i++) {
					configId = configList.get(i).getId();
					configIds = configIds + "," + configId;
					List<CompanyInfoFeedback> list = feedbackService.getClassTemplate(configId);
					CompanyInfoFeedback function = new CompanyInfoFeedback();
					function.setTempleteList(list);
					function.setConfigId(configId);
					templeteList.add(function);
				}
			}
			
			if(configIds.length() > 0){
				configIds = configIds.substring(1, configIds.length());
			}
			
			model.addAttribute("configIds", configIds);
			model.addAttribute("configList", configList);
			model.addAttribute("templeteList", templeteList);
			model.addAttribute("businessLicensePath", businessLicensePath);
			model.addAttribute("idCardPath", idCardPath);
			model.addAttribute("employeeCard", employeeCard);
			model.addAttribute("businessCard", businessCard);
			model.addAttribute("companyId", companyId);
			model.addAttribute("entName", entName);
			return ZZXQ;
		} catch (Exception e) {
			e.printStackTrace();
			return ZZXQ;
		}
		
	}
	
	/**
	 * 资质审核
	 * 通过 	1		
	 * 不通过  2
	 * @param request
	 * @param companyId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/zzsh", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Json zzsh(HttpServletRequest   request, String companyId, 
			String oper, ModelMap model, CompanyInfoFeedback feedback, String configIds) {
		Json json = new Json();
		try {
			ShiroUser shiroUser = SecurityUtils.getShiroUser();
			if(shiroUser == null) return json;
			if(StringUtil.isNotEmpty(oper) && StringUtil.isNotEmpty(companyId)){
				CompanyInfo info = new CompanyInfo();
				String feedbackId = UuidUtil.getUUID();
				info.setId(companyId);
				info.setVerifyStatus(Byte.valueOf(oper));
				switch (oper) {
				case "1": info.setFeedbackId(feedbackId);
					break;
				case "2": info.setFeedbackId("");
					break;
				default:
					info.setFeedbackId("");
					break;
				}
				
				if(feedback.getTempleteList().size() > 0){//
					String content = "";
					boolean flagRelation = false;
					for(int i = 0; i < feedback.getTempleteList().size(); i++) {
						content = feedback.getTempleteList().get(i).getContent();
						if(StringUtil.isNotEmpty(content)){
							if(StringUtil.isNotEmpty(content)){
								CompanyInfoFeedback function = new CompanyInfoFeedback();
								function.setConfigId(feedback.templeteList.get(i).getConfigId());
								function.setFeedbackId(feedbackId);
								function.setContent(content);
								function.setCreateTime(TimeUtil.currentTimeMillis());
								feedbackService.insertFeedbackRelation(function);
								flagRelation = true;
							}
						}
					}
					
					insertFeedback(shiroUser, companyId, feedbackId);
					
					boolean flag = companyInfoService.updateByPrimaryKeySelective(info);
					json.setSuccess(flag);
					//创建索引
					if(flag && oper.equals(Constant.VALUE_TWO)){
						
						LuceneUtil.luceneOpt(companyId, Constant.VALUE_ZERO, Constant.VALUE_ONE);
					}
					if(flag && oper.equals(Constant.VALUE_ONE)){
						
						LuceneUtil.luceneOpt(companyId, Constant.VALUE_ZERO, Constant.VALUE_ZERO);
						
					}
				}
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	public boolean insertFeedback(ShiroUser shiroUser , String companyId, String feedbackId){
		CompanyInfoFeedback feedbacks = new CompanyInfoFeedback();
		feedbacks.setId(feedbackId);
		feedbacks.setCompanyId(companyId);
		feedbacks.setCreateTime(TimeUtil.currentTimeMillis());
		feedbacks.setPlatformUserId(shiroUser.getId());
		return feedbackService.insertFeedback(feedbacks);
	}
	/**
	 * 创建索引
	 * @param url
	 * @param function
	 */
	public void createIndex(String url, CompanyInfo function, String taskId){
		try {
			
			Map<String, Object> mapLunece = new HashMap<String, Object>();
			String luneceTaskId = taskId;
			mapLunece.put("id", luneceTaskId);
			mapLunece.put("obj_id", function.getId());//资源id
			mapLunece.put("obj_type", 0);//对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
			mapLunece.put("opt", 0);//操作类型(0-add,1-delete,2-update
			mapLunece.put("create_time", TimeUtil.currentTimeMillis()+"");//
			mapLunece.put("status", 0);//处理状态（0-未处理,1-已处理
			mapLunece.put("handle_time", TimeUtil.currentTimeMillis()+"");//处理时间
			
			PropUtil pu = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			
			String result = HttpPostUtil.doPost(pu.getValue(AppSearchConstant.SEARCH_URL)
					+ AppSearchConstant.SEARCH_TASK_URL, mapLunece);
			
			String code = JsonUtil.jsonToObj(result, StautsBean.class).getCode();
			
			if(StringUtil.isNotEmpty(code) && code.equals(Constant.VALUE_ZERO)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(AppSearchConstant.APP_SEARCH_ID, function.getId());
				map.put(AppSearchConstant.ENT_NAME, function.getEntName());
				map.put(AppSearchConstant.OFFICE_AREA, function.getOfficeArea());
				map.put(AppSearchConstant.INDUSTRY_CODE, function.getIndustryCode());
				map.put(AppSearchConstant.LAST_UPDATED_TIME, function.getLastUpdatedTime());
				
				String s = HttpPostUtil.doPost(pu.getValue(AppSearchConstant.SEARCH_URL) 
						+ AppSearchConstant.APP_SEARCH_COMPANY_CREATE + luneceTaskId, map);
				
				System.out.println("索引创建信息： " + s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
	public Json enable(HttpServletRequest   request, String companyId, String oper) {
		Json json = new Json();
		try {
			if(StringUtil.isNotEmpty(companyId)){
				CompanyInfo info = new CompanyInfo();
				info.setId(companyId);
				info.setEnable(Byte.valueOf(oper));
				boolean flag = companyInfoService.updateByPrimaryKeySelective(info);
				
				//创建索引
				if(flag){
					String taskId = UuidUtil.getUUID();
					if(oper.equals(Constant.VALUE_ZERO)){
						
						PropUtil pu = new PropUtil(Constant.PRO_FILE_CONSTANTS);
						
						LuceneUtil.luceneOpt(companyId, Constant.VALUE_ZERO, Constant.VALUE_ONE);
//						HttpPostUtil.sendPost(pu.getValue(AppSearchConstant.SEARCH_URL)
//								+ AppSearchConstant.APP_SEARCH_COMPANY_DELETE + info.getId() + "?task_id=" + taskId, null);
					}else if(oper.equals(Constant.VALUE_ONE)){
						CompanyInfo infos = companyInfoService.selectByPrimaryKey(companyId);
//						createIndex(AppSearchConstant.APP_SEARCH_COMPANY_CREATE, infos, taskId);
						
						LuceneUtil.luceneOpt(companyId, Constant.VALUE_ZERO, Constant.VALUE_ZERO);
					}
					
				}
				
				json.setSuccess(flag);
				json.setObj(info);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(ServletRequest request, Map<String, Object> map) {

		return ADD;
	}
}
