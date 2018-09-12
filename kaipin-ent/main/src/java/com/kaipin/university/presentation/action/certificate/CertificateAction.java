package com.kaipin.university.presentation.action.certificate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Service.Mode;

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
import com.kaipin.university.model.user.SchoolInfo;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.SchoolUserInfo;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.ISchoolInfoService;
import com.kaipin.university.service.user.ISchoolUserInfoService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;

/**
 * 资质认证
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/certificate")
public class CertificateAction extends BaseAction{
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
	private IBaseCodeService baseCodeService;
	
	/**
	 * 从首页点击，查看具体的资质认证
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/certificate")
	public String certificate(HttpServletRequest request, Model model,
				String target){
		try {
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			
			String page = "";
			String schName = "学校名称";
			if(StringUtil.isEmpty(target)){//上传首页
				SchoolInfo info = schInfoService.selectByPrimaryKey(cookie_sid);
				
				if(info != null){
					schName = info.getSchoolName();
				}
				model.addAttribute("orgName", schName);
				page = "/sch/certificate/certificate_index_sch";
				
			}else if(target.equals("certificate_show")){//审核页面
				page = "/sch/certificate/certificate_check_sch";
				
			}else if(target.equals("certificate")){//上传资质页面
				page = "sch/certificate/certificate_sch";
			}
			model.addAttribute("uid", cookie_uid);
			model.addAttribute("orgId", cookie_sid);
			
			this.setSysAttr(model, schName, null, null);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 资质认证  学校
	 * @return
	 */
	@RequestMapping(value=("/certificateSch"))
	@ResponseBody
	public Json certificateSch(HttpServletRequest request, String sfz, String jyxkz,
			String gzz, String mp, String uploadAgin, String userId, String orgId){ 
		Json json = new Json();
		try {
			userId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			long time = TimeUtil.currentTimeMillis();
			if(StringUtil.isEmpty(userId)) return json;
			boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false;
			if(StringUtil.isEmpty(uploadAgin) || uploadAgin.equals("regedit")){
				//删除该账户下的资质文件
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("sch_user_id", userId);
				schBaseUserInfoService.deleteDoc(map);
				if(StringUtil.isNotEmpty(jyxkz)){//教育许可证
					flag2 = insertSchDoc(userId, time, jyxkz, "licence_card");
				}
				if(StringUtil.isNotEmpty(sfz)){//身份证
					flag1 = insertSchDoc(userId, time, sfz, "id_card");
				}
				if(StringUtil.isNotEmpty(gzz)){//工作证
					flag3 = insertSchDoc(userId, time, gzz, "employee_card");
				}
				if(StringUtil.isNotEmpty(mp)){//名片
					flag4 = insertSchDoc(userId, time, mp, "business_card");
				}
				if(flag1 || flag2 || flag3 || flag4){
					
					//更改状态 更改反馈问题
					SchoolInfoLink link = new SchoolInfoLink();
					link.setId(localUserService.selectByPrimaryKey(userId).getOrganizationId());
					link.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
					link.setFeedbackId("");
					schLinkService.updateByPrimaryKeySelective(link);
					json.setSuccess(true);
				}
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	
	public boolean insertSchDoc(String userId, long time, String path, String type){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sch_user_id", userId);
			map.put("last_updated_time", time);
			map.put("create_time", time);
			map.put("document_path", path);
			map.put("document_type", type);
			schBaseUserInfoService.insertDoc(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 资质审核详情页面	 学校
	 * @param request
	 * @param usrtId
	 * @param orgId
	 * @param model
	 * @return
	 */
	@RequestMapping(value=("/checkPageSch"))
	public String checkPageSch(HttpServletRequest request, String userId,
			String orgId, Model model, String uploadAgin){
		try {
			userId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			orgId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			UserLocalauth user = new UserLocalauth();
			user = localUserService.selectByPrimaryKey(userId);
			
			SchoolUserInfo schUser = schUserInfoService.selectByPrimaryKey(userId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sch_user_id", userId);
			List<Map<String,Object>> listDoc = schBaseUserInfoService.selectDoc(userId);
			model.addAttribute("user", user);
			model.addAttribute("userInfo", schUser);
			String checkRuslt = "";
			SchoolInfoLink link = schLinkService.selectByPrimaryKey(orgId);
			
			List<Map<String,Object>> feedbackList = new ArrayList<>();
			String location = "";
			if(link != null){
				String status = link.getVerifyStatus()+"";
				if(status.equals(Constant.VALUE_ZERO)){
					checkRuslt = "待审核";
				}else if(status.equals(Constant.VALUE_ONE)){
					checkRuslt = "未通过";
				}else if(status.equals(Constant.VALUE_TWO)){
					checkRuslt = "已通过";
				}
				
				String verifyStatus = String.valueOf(link.getVerifyStatus());
				String feedbackId = link.getFeedbackId();
				//查询审核反馈意见
				if(StringUtil.isNotEmpty(verifyStatus) 
						&& StringUtil.isNotEmpty(feedbackId)
						&& verifyStatus.equals(Constant.VALUE_ONE) ){
					map.clear();
					map.put("feedbackId", feedbackId);
					feedbackList = schBaseUserInfoService.getFeedbackList(map);
				}
				location = baseCodeService.getAreaAllNameByCode(link.getLocationCode());
			}
			
			
			
			model.addAttribute("location", location);
			model.addAttribute("uploadAgin", uploadAgin);
			model.addAttribute("checkRuslt", checkRuslt);
			model.addAttribute("listDoc", listDoc);
			
			
			model.addAttribute("feedbackList", feedbackList);
			model.addAttribute("link", link);
			
			
			model.addAttribute("userId", userId);
			model.addAttribute("orgId", orgId);
			
			SchoolInfo info = schInfoService.selectByPrimaryKey(orgId);
			String schName = "企业名称";
			if(info != null){
				schName = info.getSchoolName();
			}
			this.setSysAttr(model, schName, null, null);
			
			return "/sch/certificate/certificate_check_sch";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	/**
	 * 首页检查有没有资质认证
	 * @return
	 */
	@RequestMapping("/checkCertificate")
	@ResponseBody
	public Json checkCertificate(HttpServletRequest request, User user){
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sch_user_id", super.getOrgId(request, user));
			List<Map<String,Object>> list = schBaseUserInfoService.checkCertificate(map);
			if(list.size() > 0){
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
		
	}
	
	
	/**
	 * 其他组织查看学校资质
	 * @param request
	 * @param usrtId
	 * @param orgId
	 * @param model
	 * @return
	 */
	@RequestMapping(value=("/certificate-university-show"))
	public String certificateSchShow(HttpServletRequest request, Model model, String uploadAgin, User users){
		try {
			String orgId = this.getOrgId(request, users);
			UserLocalauth user = new UserLocalauth();
			
			user = localUserService.selectByOrgId(orgId);
			String userId = user.getId();
			SchoolUserInfo schUser = schUserInfoService.selectByPrimaryKey(userId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sch_user_id", userId);
			List<Map<String,Object>> listDoc = schBaseUserInfoService.selectDoc(userId);
			model.addAttribute("user", user);
			model.addAttribute("userInfo", schUser);
			String checkRuslt = "";
			SchoolInfoLink link = schLinkService.selectByPrimaryKey(orgId);
			
			String location = "";
			if(link != null){
				String status = link.getVerifyStatus()+"";
				if(status.equals(Constant.VALUE_ZERO)){
					checkRuslt = "待审核";
				}else if(status.equals(Constant.VALUE_ONE)){
					checkRuslt = "未通过";
				}else if(status.equals(Constant.VALUE_TWO)){
					checkRuslt = "已通过";
				}
				
				location = baseCodeService.getAreaAllNameByCode(link.getLocationCode());
			}
			
			
			
			model.addAttribute("location", location);
			model.addAttribute("uploadAgin", uploadAgin);
			model.addAttribute("checkRuslt", checkRuslt);
			model.addAttribute("listDoc", listDoc);
			
			
			model.addAttribute("link", link);
			
			
			model.addAttribute("userId", userId);
			model.addAttribute("orgId", orgId);
			
			SchoolInfo info = schInfoService.selectByPrimaryKey(orgId);
			String schName = "学校名称";
			if(info != null){
				schName = info.getSchoolName();
			}
			this.setSysAttr(model, schName, null, null);
			
			return "/sch/certificate/certificate_show_sch";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

}
