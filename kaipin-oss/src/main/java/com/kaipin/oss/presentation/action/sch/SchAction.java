package com.kaipin.oss.presentation.action.sch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.kaipin.oss.model.company.CompanyDocument;
import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.model.company.CompanyInfoFeedback;
import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.model.sch.SchBaseInfo;
import com.kaipin.oss.model.sch.SchInfoFeedback;
import com.kaipin.oss.model.sch.SchoolInfoLink;
import com.kaipin.oss.presentation.action.Constants;
import com.kaipin.oss.security.SecurityUtils;
import com.kaipin.oss.security.ShiroUser;
import com.kaipin.oss.service.company.CompanyInfoFeedbackService;
import com.kaipin.oss.service.sch.ISchBaseInfoService;
import com.kaipin.oss.service.sch.ISchInfoFeedbackService;
import com.kaipin.oss.service.sch.ISchoolInfoLinkService;
import com.kaipin.oss.util.HttpPostUtil;
import com.kaipin.oss.util.JsonUtil;
import com.kaipin.oss.util.LuceneUtil;
import com.kaipin.oss.util.PropUtil;
import com.kaipin.oss.util.StringUtil;
import com.kaipin.oss.util.TimeUtil;
import com.kaipin.oss.util.UuidUtil;

/**
 * 学校
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/management/sch/main")
public class SchAction {

	private final String LIST = "sch/list";//高校管理列表
	private final String ADD = "sch/add";//新增页面
	private final String DETAIL = "sch/detail";//高校详情
	private final String ZZSH_LISt = "sch/zzsh/list";//高校资质审核
	private final String ZZXQ = "sch/zzsh/zzxq";//高校资质详情
	
	@Autowired
	private ISchBaseInfoService schBserService;
	@Autowired
	private ISchInfoFeedbackService feedbackService;//反馈
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	
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
			
			IGenericPage<SchBaseInfo> pages = 
					schBserService.getSchDocList(param, GenericDefaultPage.cpn(pageNo),
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
	public String zzxq(HttpServletRequest   request,String schoolId, String userId, ModelMap model, String schName) {
		try {
			String businessLicensePath ="";
			String idCardPath ="";
			String employeeCard ="";
			String businessCard ="";
			if(StringUtil.isNotEmpty(userId)){
				List<Map<String, Object>> doc = schBserService.getSchDocList(userId);
				if(doc.size() > 0){
					for (int i = 0; i < doc.size(); i++) {
						String type = doc.get(i).get("document_type")+"";
						String path = doc.get(i).get("document_path")+"";
						if(type.equals("licence_card")){//营业执照
							businessLicensePath = path;
						}else if(type.equals("id_card")){//身份证
							idCardPath = path;
						}else if(type.equals("employee_card")){//雇佣证明
							employeeCard = path;
						}else if(type.equals("business_card")){//名片
							businessCard = path;
						}
						
					}
				}
			}
			
			//模板id
			String configIds = "";
			//获取配置list
			List<SchInfoFeedback> configList = feedbackService.getClassConfig();
			List<SchInfoFeedback> templeteList = new ArrayList<SchInfoFeedback>();
			//获取模板list
			String configId = "";
			if(configList.size() > 0){
				configId = "";
				for (int i = 0; i < configList.size(); i++) {
					configId = configList.get(i).getId();
					configIds = configIds + "," + configId;
					List<SchInfoFeedback> list = feedbackService.getClassTemplate(configId);
					SchInfoFeedback function = new SchInfoFeedback();
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
			model.addAttribute("schoolId", schoolId);
			model.addAttribute("entName", schName);
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
	public Json zzsh(HttpServletRequest   request, String schoolId, 
			String oper, ModelMap model, SchInfoFeedback feedback, String configIds, String schoolName) {
		Json json = new Json();
		try {
			ShiroUser shiroUser = SecurityUtils.getShiroUser();
			if(shiroUser == null) return json;
			if(StringUtil.isNotEmpty(oper) && StringUtil.isNotEmpty(schoolId)){
				SchoolInfoLink info = new SchoolInfoLink();
				String feedbackId = UuidUtil.getUUID();
				info.setId(schoolId);
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
								SchInfoFeedback function = new SchInfoFeedback();
								function.setConfigId(feedback.templeteList.get(i).getConfigId());
								function.setFeedbackId(feedbackId);
								function.setContent(content);
								function.setCreateTime(TimeUtil.currentTimeMillis());
								feedbackService.insertFeedbackRelation(function);
								flagRelation = true;
							}
						}
					}
					
					insertFeedback(shiroUser, schoolId, feedbackId);
					
					int flag = schLinkService.updateByPrimaryKeySelective(info);
					if(flag == 1){
						json.setSuccess(true);
					}
					//创建索引
					if(oper.equals(Constant.VALUE_TWO)){
						
						LuceneUtil.luceneOpt(schoolId, Constant.VALUE_FOUR, Constant.VALUE_ZERO);
						
					}
					if(oper.equals(Constant.VALUE_ONE)){
						
						LuceneUtil.luceneOpt(schoolId, Constant.VALUE_FOUR, Constant.VALUE_ONE);
					}
				}
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	public boolean insertFeedback(ShiroUser shiroUser , String schoolId, String feedbackId){
		SchInfoFeedback feedbacks = new SchInfoFeedback();
		feedbacks.setId(feedbackId);
		feedbacks.setSchoolId(schoolId);
		feedbacks.setCreateTime(TimeUtil.currentTimeMillis());
		feedbacks.setPlatformUserId(shiroUser.getId());
		return feedbackService.insertFeedback(feedbacks);
	}
	
	
	public void createIndex(String url, String taskId, String schoolName, String schoolId){
		Map<String, Object> mapLunece = new HashMap<String, Object>();
		String luneceTaskId = taskId;
		mapLunece.put("id", luneceTaskId);
		mapLunece.put("obj_id", schoolId);//资源id
		mapLunece.put("obj_type", 4);//对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
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
			param.put("id", schoolId);
			param.put("school_name", schoolName);
			param.put("create_time", TimeUtil.currentTimeMillis());
			
			String s = HttpPostUtil.doPost(pro.getValue(AppSearchConstant.SEARCH_URL) 
					+ AppSearchConstant.APP_SEARCH_SCH_CREATE + luneceTaskId, param);
			System.out.println("sch 索引创建信息： " + s);
		}
	}
	
}
