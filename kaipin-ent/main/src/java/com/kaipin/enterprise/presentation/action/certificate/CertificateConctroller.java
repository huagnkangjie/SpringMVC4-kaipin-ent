package com.kaipin.enterprise.presentation.action.certificate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.model.user.CompanyUserInfo;
import com.kaipin.enterprise.service.certificate.ICertificateService;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.enterprise.service.user.ICompanyUserInfoService;
import com.kaipin.enterprise.service.user.IEntBaseUserService;
import com.kaipin.university.model.user.SchoolInfo;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.IUserLocalauthService;

/**
 * 企业资质认证
 * @author Mr-H
 *
 */
@RequestMapping("/certificate")
@Controller
public class CertificateConctroller extends BaseAction{

	public static String CERTIFICATE_SHOW_PAGE = "/ent/certificate/certificate_show_ent";
	
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private ICertificateService certificateService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private IBaseCodeService service;
	@Autowired
	private ICompanyUserInfoService companyUserInfoService;
	@Autowired
	private IEntBaseUserService entBaseService;
	@Autowired
	private IBaseCodeService baseCodeService;
	
	
	/**
	 * 从首页点击，查看具体的资质认证
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/certificate-company")
	public String certificate(HttpServletRequest request, Model model,
				String target){
		try {
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			
			String page = "";
			String schName = "企业名称";
			CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_sid);
			
			if(info != null){
				schName = info.getEntName();
			}
			if(StringUtil.isEmpty(target)){//上传首页
				
				model.addAttribute("orgName", schName);
				page = "/ent/certificate/certificate_index_ent";
				
			}else if(target.equals("certificate_show")){//审核页面
				page = "/ent/certificate/certificate_check_ent";
				
			}else if(target.equals("certificate")){//上传资质页面
				page = "/ent/certificate/certificate_ent";
			}
			model.addAttribute("uid", cookie_uid);
			model.addAttribute("orgId", cookie_sid);
			
			model.addAttribute("userId", cookie_uid);
			
			this.setSysAttr(model, schName, null, null);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 学校、学生，查看企业资质认证页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/certificate-company-show")
	public String certificateShowPage(HttpServletRequest request, Model model, User user){
		try {
			String org_id = this.getOrgId(request, user);
			CompanyInfo companyInfo = companyInfoService.selectByPrimaryKey(org_id);
			UserLocalauth localUser = localUserService.selectByOrgId(org_id);
			CompanyUserInfo companyUserInfo = companyUserInfoService.selectByPrimaryKey(localUser.getId());
			String verify_status = Constant.VALUE_ZERO;
			String area = "";
			String entName = "企业";
			List<Map<String,Object>> listDoc = new ArrayList<>();
			if(companyInfo != null){
				verify_status = String.valueOf(companyInfo.getVerifyStatus());
				Map<String,Object> map = new HashMap<>();
				if(verify_status.equals(Constant.VALUE_TWO)){
					map.put("userId", localUser.getId());
					listDoc = certificateService.getEntDoc(map);
				}
				if(StringUtil.isNotEmpty(companyInfo.getOfficeArea())){
					area = service.getAreaAllNameByCode(companyInfo.getOfficeArea());
				}
				entName = companyInfo.getEntName();
				if(StringUtil.isEmpty(entName)){
					entName = "企业";
				}
			}
			model.addAttribute("verify_status", verify_status);
			model.addAttribute("localUser", localUser);
			model.addAttribute("companyUserInfo", companyUserInfo);
			model.addAttribute("area", area);//所在地区
			model.addAttribute("status", "已通过");//状态
			model.addAttribute("listDoc", listDoc);//状态
			this.setSysAttr(model, entName + " - 资质文件", null, null);
			return CERTIFICATE_SHOW_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
			return CERTIFICATE_SHOW_PAGE;
		}
	}
	
	
	/**
	 * 企业资质审核	企业
	 * @param request
	 * @param yyzz
	 * @param sfz
	 * @param gzz
	 * @param mp
	 * @return
	 */
	@RequestMapping(value=("/certificateEnt"))
	@ResponseBody
	public Json certificateEnt(HttpServletRequest request, String yyzz, String sfz,
			String gzz, String mp, String uploadAgin, String userId, String orgId){ 
		Json json = new Json();
		try {
			long time = TimeUtil.currentTimeMillis();
			if(StringUtil.isEmpty(userId)) return json;
			boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false;
			if(StringUtil.isEmpty(uploadAgin) || uploadAgin.equals("regedit")){
				//删除该账户下的资质文件
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("ent_user_id", userId);
				entBaseService.deleteDoc(map);
				if(StringUtil.isNotEmpty(yyzz)){//营业执照
					flag1 = insertEntDoc(userId, time, yyzz, "business_license");
				}
				if(StringUtil.isNotEmpty(sfz)){//身份证
					flag2 = insertEntDoc(userId, time, sfz, "id_card");
				}
				if(StringUtil.isNotEmpty(gzz)){//工作证
					flag3 = insertEntDoc(userId, time, gzz, "employee_card");
				}
				if(StringUtil.isNotEmpty(mp)){//名片
					flag4 = insertEntDoc(userId, time, mp, "business_card");
				}
				if(flag1 || flag2 || flag3 || flag4){
					json.setSuccess(true);
					
					CompanyInfo info = new CompanyInfo();
					UserLocalauth user = localUserService.selectByPrimaryKey(userId);
					info.setId(user.getOrganizationId());
					info.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
					info.setFeedbackId(null);
					companyInfoService.updateByPrimaryKeySelective(info);
				}
			}
			
		
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	
	public boolean insertEntDoc(String userId, long time, String path, String type){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ent_user_id", userId);
			map.put("last_updated_time", time);
			map.put("create_time", time);
			map.put("document_path", path);
			map.put("document_type", type);
			entBaseService.insertDoc(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	/**
	 * 资质审核详情页面
	 * @param request
	 * @param usrtId
	 * @param orgId
	 * @param model
	 * @return
	 */
	@RequestMapping(value=("/checkPageEnt"))
	public String checkPage(HttpServletRequest request, String userId,
			String orgId, Model model, String uploadAgin){
		try {
			User users = new User();
			if(StringUtil.isEmpty(userId)){
				userId = this.getUid(request, users);
			}
			if(StringUtil.isEmpty(orgId)){
				orgId = this.getOrgId(request, users);
			}
			
			CompanyInfo info = new CompanyInfo();
			UserLocalauth user = new UserLocalauth();
			CompanyUserInfo userInfo = new CompanyUserInfo();
			if(StringUtil.isNotEmpty(userId)){
				user = localUserService.selectByPrimaryKey(userId);
				userInfo = companyUserInfoService.selectByPrimaryKey(userId);
			}
			if(StringUtil.isNotEmpty(orgId)){
				info = companyInfoService.selectByPrimaryKey(orgId);
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ent_user_id", userId);
			List<Map<String,Object>> listDoc = entBaseService.selectDoc(map);
			model.addAttribute("info", info);	
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("user", user);
			String checkRuslt = "";
			String status = info.getVerifyStatus()+"";
			List<Map<String,Object>> feedbackList = new ArrayList<>();
			if(status.equals(Constant.VALUE_ZERO)){
				checkRuslt = "待审核";
			}else if(status.equals(Constant.VALUE_ONE)){
				checkRuslt = "未通过";
				
				String feedbackId = info.getFeedbackId();
				//查询审核反馈意见
				if(StringUtil.isNotEmpty(status) 
						&& StringUtil.isNotEmpty(feedbackId)
						&& status.equals(Constant.VALUE_ONE) ){
					map.clear();
					map.put("feedbackId", feedbackId);
					
					feedbackList = entBaseService.getFeedbackList(map);
				}
			}else if(status.equals(Constant.VALUE_TWO)){
				checkRuslt = "已通过";
			}
			
			String locationCode = info.getOfficeArea();
			String location = "无";
			if(StringUtil.isNotEmpty(locationCode)){
				location = baseCodeService.getAreaAllNameByCode(locationCode);
			}
			
			
			model.addAttribute("location", location);
			
			model.addAttribute("uploadAgin", uploadAgin);
			model.addAttribute("checkRuslt", checkRuslt);
			model.addAttribute("listDoc", listDoc);
			
			model.addAttribute("feedbackList", feedbackList);
			
			
			model.addAttribute("userId", userId);
			model.addAttribute("orgId", orgId);
			
			model.addAttribute("info", info);
			
			
			this.setSysAttr(model, info.getEntName(), null, null);
			return "/ent/certificate/certificate_check_ent";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	
}
