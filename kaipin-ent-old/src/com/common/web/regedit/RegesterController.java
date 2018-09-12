package com.common.web.regedit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.ConstantTables;
import com.common.constants.AppSearchConstant;
import com.common.pojo.Json;
import com.common.pojo.StautsBean;
import com.enterprise.model.common.EntUser;
import com.enterprise.model.user.CompanyInfo;
import com.enterprise.model.user.CompanyUserInfo;
import com.enterprise.model.user.UserLocalauth;
import com.enterprise.service.common.IBaseService;
import com.enterprise.service.login.IEntBaseinfoService;
import com.enterprise.service.user.ICompanyInfoService;
import com.enterprise.service.user.ICompanyUserInfoService;
import com.enterprise.service.user.IEntBaseUserService;
import com.enterprise.service.user.IUserLocalauthService;
import com.util.CacheUtil;
import com.util.CookieUtil;
import com.util.DESCoderUtil;
import com.util.HttpPostUtil;
import com.util.HttpRequestUtil;
import com.util.JsonUtil;
import com.util.LogUtil;
import com.util.MD5Util;
import com.util.PropUtil;
import com.util.SendHtmlMailUtil;
import com.util.StringUtil;
import com.util.TimeUtil;
import com.util.UuidUtil;

/**
 * 注册
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("regedit")
public class RegesterController {
	
	Logger log = Logger.getLogger(RegesterController.class.getName());
	
	@Autowired
	private IEntBaseinfoService entBaseinfoService;
	@Autowired
	private ICompanyUserInfoService userService;
	@Autowired
	private ICompanyInfoService infoService;
	@Autowired
	private IEntBaseUserService baseUserService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private IBaseService baseService;
	@Autowired
	private IUserLocalauthService localUserService;
	
	
	/**
	 * 注册页面初始化
	 * 找回密码初始化页面
	 * @return
	 */
	@RequestMapping({"/init"})
	public String init(HttpServletRequest request, String oper){
		try {
			if(StringUtil.isEmpty(oper)){
				return "/ent/regedit/regedit_index";
			}else if(oper.equals("backPw")){//找回密码页面
				return "/ent/regedit/backPw";
			}else if(oper.equals("stu")){//学生注册页面初始化
				return "/ent/regedit/regedit_stu";
			}else if(oper.equals("ent")){//企业注册页面初始化
				return "/ent/regedit/regedit_ent";
			}
			return null;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping({"/register"})
	@ResponseBody
	public Json register(HttpSession session, CompanyUserInfo user, CompanyInfo info, String oper){
		try {
			String companyId = UuidUtil.getUUID();
			String userId = UuidUtil.getEntUUID();
			int flag = 0;
			if(oper.equals(Constant.USER_ENT)){
				user.setId(userId);
				info.setId(companyId);
				//密码加密
				String password = user.getPassword();
				user.setPassword(MD5Util.encrypt(password));
				user.setEncodePassword(DESCoderUtil.deCode(password));
				//用户大写转小写
				user.setEmail(user.getEmail().toLowerCase());
				info.setEmail(user.getEmail().toLowerCase());
				//注册
				flag = registerEnt(user, info);
			}
			Json json = new Json();
			if(flag == 1){
				//创建索引
				//createIndex(AppSearchConstant.APP_SEARCH_COMPANY_CREATE, info);
				
				SendHtmlMailUtil.setIndex(Constant.VALUE_ZERO);//获取邮件服务from 的索引			
				while(true){
					int flags = SendHtmlMailUtil.sendCheakMail(userId, user.getEmail());
					if(flags == 550){
						SendHtmlMailUtil.setIndex("add");//获取邮件服务from 的索引+1
					}else if(flags == 200){
						Map<String,String> map = new HashMap<String, String>();
						map.put("email", user.getEmail());
						map.put("userId", userId);
						map.put("companyId", companyId);
						json.setObj(map);
						json.setSuccess(true);
						break;
					}else if(flags != 550 ){
						break;
					}
				}
				
			}
//			session.setAttribute(Constant.USER, user);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 创建索引
	 * @param url
	 * @param function
	 */
	public void createIndex(String url, CompanyInfo function){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put(AppSearchConstant.APP_SEARCH_ID, function.getId());
			map.put(AppSearchConstant.ENT_NAME, function.getEntName());
			map.put(AppSearchConstant.OFFICE_AREA, function.getOfficeArea());
			map.put(AppSearchConstant.INDUSTRY_CODE, function.getIndustryCode());
			map.put(AppSearchConstant.LAST_UPDATED_TIME, function.getLastUpdatedTime());
			
			PropUtil prop = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			
			HttpPostUtil.doPost(prop.getValue(AppSearchConstant.APP_SEARCH_URL) 
					+ url, map);
			
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 创建企业用户
	 * @param userInfo
	 * @return
	 */
	public int registerEnt(CompanyUserInfo userInfo, CompanyInfo info){
		try {
			int flag = baseUserService.createEntUser(userInfo, info);
			if(flag == 1){
				return 1;
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	/**
	 * 提交认证信息
	 * @return
	 */
	@RequestMapping({"/certificateSave"})
	@ResponseBody
	public Json certificateSave(HttpSession session, HttpServletResponse response,
			HttpServletRequest request,
			CompanyUserInfo user, CompanyInfo info, 
			Model model, String userId, String companyId, String licenceImg, String idCardImg, 
			String yyzz, String sfz, String gzz, String mp,
			String status, String uploadAgin){
		Json json = new Json();
		try {
			//user.setId(userId);
			//info.setId(companyId);
			//infoService.updateByPrimaryKeySelective(info);
			//int m = userService.updateByPrimaryKeySelective(user);
			
			//审核不通过、重新上传
			if(uploadAgin.equals("uploadAgin")){
				//查询历史的资质情况
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("ent_user_id", userId);
				List<Map<String,Object>> listDoc = baseUserService.selectDoc(map);
				/*if(listDoc.size() > 0){
					//把数据更新到历史表中
					map.put("business_license_code", listDoc.get(0).get("business_license_code"));
					map.put("business_license_path", listDoc.get(0).get("business_license_path"));
					map.put("id_card_path", listDoc.get(0).get("id_card_path"));
					map.put("last_updated_time", TimeUtil.currentTimeMillis());
					map.put("create_time", listDoc.get(0).get("create_time"));
					baseUserService.insertDocHistory(map);
				}
				//更新新的资质情况
				map.clear();
				map.put("companyId", companyId);
				map.put("business_license_code", "");
				map.put("business_license_path", licenceImg);
				map.put("id_card_path", idCardImg);
				map.put("last_updated_time", TimeUtil.currentTimeMillis());
				map.put("company_id", companyId);
				map.put("create_time", TimeUtil.currentTimeMillis());
				int flag = baseUserService.updateDoc(map);*/
				//保存资质
				//删除该账户下的资质文件
				boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false;
				
				//更新主表
				if(flag1 || flag2 || flag3 || flag4){
					CompanyInfo infos = new CompanyInfo();
					infos.setId(companyId);
					infos.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
					infos.setFeedbackId("");
					companyInfoService.updateByPrimaryKeySelective(infos);
				}
				
				//String url = request.getContextPath();
				//response.sendRedirect(url + "/regedit/certificateCheckPage.do?companyId="+companyId+"&userId="+userId+"&status=1");
			}else{
				user = userService.selectByPrimaryKey(userId);
				info = infoService.selectByPrimaryKey(companyId);
				boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false;
				long time = TimeUtil.currentTimeMillis();
				//保存资质
				//删除该账户下的资质文件
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("ent_user_id", userId);
				baseUserService.deleteDoc(map);
				
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
				}
				
				//更新主表
				CompanyInfo infos = new CompanyInfo();
				infos.setId(companyId);
				infos.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
				infos.setFeedbackId("");
				companyInfoService.updateByPrimaryKeySelective(infos);
			}
				
			model.addAttribute("user", user);
			model.addAttribute("info", info);
			model.addAttribute("licenceImg", licenceImg);
			model.addAttribute("idCardImg", idCardImg);
			model.addAttribute("status", status);//用于判断返回按钮 0 返回登录 1 返回首页
			
//				String url = request.getContextPath();
//				response.sendRedirect(url + "/regedit/certificateCheckPage.do?companyId="+companyId+"&userId="+userId+"&status="+status);
		
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
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
			baseUserService.insertDoc(map);
			return true;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return false;
			
		}
	}
	
	/**
	 * 加载审核页面
	 * @return
	 */
	@RequestMapping({"/certificateCheckPage"})
	public String certificateCheckPage(Model model,String companyId, String userId, String status){
		try {
			CompanyUserInfo user = new CompanyUserInfo();
			CompanyInfo info = new CompanyInfo();
			UserLocalauth userLocal = new UserLocalauth();
			String licenceImg = "";
			String idCardImg = "";
			String fail = "";
			List<Map<String,Object>> list = null;
			if(StringUtil.isEmpty(companyId) && StringUtil.isEmpty(userId)){
				fail = "fail";
				model.addAttribute("fail", fail);
			}else{
				
				if(StringUtil.isNotEmpty(userId)){
					user = userService.selectByPrimaryKey(userId);
					userLocal = localUserService.selectByPrimaryKey(userId);
				}
				if(StringUtil.isNotEmpty(companyId)){
					info = companyInfoService.selectByPrimaryKey(companyId);
				}
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("ent_user_id", userId);
				list = baseUserService.selectDoc(map);
			}
			model.addAttribute("userInfo", user);
			model.addAttribute("status", status);
			model.addAttribute("user", userLocal);
			model.addAttribute("listDoc", list);
			model.addAttribute("info", info);
			model.addAttribute("checkType", 0);//审核中
			model.addAttribute("checkRuslt", "审核中");//审核中
			return "/ent/regedit/ent_certificate_check";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 资质验证页面初始化
	 * @return
	 */
	@RequestMapping({"/certificate"})
	public String certificate(HttpServletRequest request, HttpSession session,Model model, String oper, 
			String target, String companyId, String userId, String checkType){
		try {
			String uploadAgin = "0";
			model.addAttribute("success", oper);
			model.addAttribute("userId", userId);
			model.addAttribute("companyId", companyId);
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			String entName = "企业名称";
			String entSimpleName = "企业简称";
			String checkRuslt = "审核中";
			String status = "0";
			if(StringUtil.isEmpty(target)){
				if(StringUtil.isNotEmpty(cookie_companyId) || StringUtil.isNotEmpty(companyId)){
					String entId = "";
					if(StringUtil.isNotEmpty(cookie_companyId)){
						entId = cookie_companyId;
					}else {
						entId = companyId;
					}
					CompanyInfo info = companyInfoService.selectByPrimaryKey(entId);
					String entNameTemp = info.getEntName();
					if(StringUtil.isNotEmpty(entNameTemp)){
						if(!entNameTemp.equals("null") && entNameTemp.length() > 0){
							entName = entNameTemp;
						}
					}
				}
				model.addAttribute("entName", entName);
				return "/ent/regedit/ent_certificate_index";
			}else if(target.equals("certificate")){//从首页点击进行审核
				if(StringUtil.isNotEmpty(cookie_companyId)){
					entName = "";
					entSimpleName = "";
					CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_companyId);
					String entNameTemp = info.getEntName();
					String entSimpleNameTemp = info.getEntSimpleName();
					if(StringUtil.isNotEmpty(entNameTemp)){
						if(!entNameTemp.equals("null") && entNameTemp.length() > 0){
							entName = entNameTemp;
						}
					}
					if(StringUtil.isNotEmpty(entSimpleNameTemp)){
						if(!entSimpleNameTemp.equals("null") && entSimpleNameTemp.length() > 0){
							entSimpleName = entSimpleNameTemp;
						}
					}
					status = "1";
					
					userId = cookie_uId;
					companyId = cookie_companyId;
					
				}else{
					entName = "";
					entSimpleName = "";
				}
				if(StringUtil.isNotEmpty(oper)){
					if(oper.equals("uploadAgin")){
						uploadAgin = "uploadAgin";
					}
				}
				model.addAttribute("userId", userId);
				model.addAttribute("companyId", companyId);
				
				model.addAttribute("orgId", companyId);
				
				model.addAttribute("uploadAgin", uploadAgin);
				model.addAttribute("status", status);
				model.addAttribute("entName", entName);
				model.addAttribute("entSimpleName", entSimpleName);
				//session.setAttribute(Constant.USER, user);
				return "/ent/regedit/ent_certificate";
			}else if(target.equals("certificate_show")){//查看当前审核的具体情况
				CompanyInfo info = new CompanyInfo();
				List<Map<String,Object>> feedbackList = null;
				List<Map<String,Object>> list = null;
				String licenceImg = "";
				String idCardImg = "";
				UserLocalauth userLoacl = new UserLocalauth();
				if(StringUtil.isNotEmpty(cookie_companyId)){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("ent_user_id", userId);
					list = baseUserService.selectDoc(map);
					userLoacl = localUserService.selectByPrimaryKey(userId);
					//如果是审核不通过，则查找不通过的原因
					CompanyInfo infos = companyInfoService.selectByPrimaryKey(cookie_companyId);
					if(list.size() > 0){
						info = companyInfoService.selectByPrimaryKey(cookie_companyId);
						
						String verifyStatus = String.valueOf(infos.getVerifyStatus());
						String feedbackId = infos.getFeedbackId();
						if(StringUtil.isNotEmpty(verifyStatus) 
								&& StringUtil.isNotEmpty(feedbackId)
								&& verifyStatus.equals(Constant.VALUE_ONE) ){
							map.clear();
							map.put("feedbackId", feedbackId);
							feedbackList = baseUserService.getFeedbackList(map);
						}
					}
					
					String verfiy_status = String.valueOf(infos.getVerifyStatus());
					if(verfiy_status.equals(Constant.VALUE_ZERO)){
						checkRuslt = "审核中";
					}else if(verfiy_status.equals(Constant.VALUE_ONE)){
						checkRuslt = "不通过";
					}else if(verfiy_status.equals(Constant.VALUE_TWO)){
						checkRuslt = "已通过";
					}
				}
				CompanyUserInfo userInfo = userService.selectByPrimaryKey(cookie_uId);
				//user.setUserName(userInfo.getUserName());
				
				model.addAttribute("status", 1);//返回企业首页
				model.addAttribute("info", info);
				model.addAttribute("user", userInfo);
				
				model.addAttribute("licenceImg", licenceImg);
				model.addAttribute("idCardImg", idCardImg);
				model.addAttribute("listDoc", list);
				model.addAttribute("user", userLoacl);
				model.addAttribute("userInfo", userInfo);
				model.addAttribute("checkType", checkType);
				
				model.addAttribute("feedbackList", feedbackList);//反馈信息
				model.addAttribute("checkRuslt", checkRuslt);//审核中
				
				return "/ent/regedit/ent_certificate_check";
			}
			return null;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 注册成功页面
	 * @return
	 */
	@RequestMapping({"/regeditSuc"})
	public String regeditSuc(Model model, String oper){
		try {
			model.addAttribute("success", oper);
			return "/ent/regedit/regedit_success";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 验证邮箱发过来的验证
	 * @return
	 */
	@RequestMapping(value="/cheackmail") 
	public String cheackmail(HttpServletRequest request, String uuid, String email, Model model){
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("id", uuid);
			map.put("email", email);
			//map.put("enable", Constant.VALUE_ZERO);
			map.put("checkMail", Constant.VALUE_ZERO);
//			EntUser user = userService.login(map); 
			List<Map<String,Object>> list = baseUserService.cheackmail(map);
			if(list.size() > 0) {
				CompanyUserInfo user = new CompanyUserInfo();
				user.setId(uuid);
				user.setIsCheckMail(Byte.valueOf(Constant.VALUE_ONE));
//				request.getSession().setAttribute(Constant.USER, user);
				userService.updateByPrimaryKeySelective(user);
				model.addAttribute("companyId", list.get(0).get("company_id"));
				model.addAttribute("userId", list.get(0).get("company_user_id"));
				return "/ent/regedit/cheakmail_success";
			}else{
				return "/ent/regedit/cheakmail_fail";
			}
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 登录邮箱未激活，重新发送邮件页面
	 * @param request
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/againMailPage")
	public String againMailPage(HttpServletRequest request, String userName, Model model){
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("userName", userName);
			String uId = "";
			List<Map<String,Object>> list = baseUserService.againMail(map);
			if(list.size() > 0){
				Object ids = list.get(0).get("id");
				if(ids != null){
					uId = String.valueOf(ids);
				}
			}
			model.addAttribute("uId", uId);
			model.addAttribute("userName", userName);
			return "/ent/regedit/aginMail";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 重新发送邮件
	 * @return
	 */
	@RequestMapping(value="/reSendMail")
	@ResponseBody
	public Json reSendMail(String uId){
		try {
			Json json = new Json();
			CompanyUserInfo userInfo = userService.selectByPrimaryKey(uId);
			if(userInfo != null){
				SendHtmlMailUtil.setIndex(Constant.VALUE_ZERO);//获取邮件服务from 的索引			
				while(true){
					int flags = SendHtmlMailUtil.sendCheakMail(userInfo.getId(), userInfo.getEmail());
					if(flags == 550){
						SendHtmlMailUtil.setIndex("add");//获取邮件服务from 的索引+1
					}else if(flags == 200){
						json.setSuccess(true);
						json.setMsg("发送成功，请前往邮箱激活！");
						break;
					}else if(flags != 550 ){
						json.setMsg("发送失败，请刷新页面重新获取！");
						break;
					}
				}
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 手机号唯一性验证
	 * @return
	 */
	@RequestMapping(value="/validataPhone")
	@ResponseBody
	public Json validataPhone(String phone){
		try {
			Json json = new Json();
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("enable", 1);
			List<Map<String,Object>> list = baseUserService.validataPhone(map);
			if(list.size() > 0){
				json.setSuccess(false);
			}else {
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 邮箱唯一性验证
	 * @return
	 */
	@RequestMapping(value="/emailValidata")
	@ResponseBody
	public Json emailValidata(String email){
		try {
			Json json = new Json();
			HashMap<String,Object> map = new HashMap<String, Object>();
//			map.put("status", Constant.VALUE_ONE);
			map.put("email", email);
			List<Map<String,Object>> list = baseUserService.emailValidata(map);
//			List<Map<String,Object>> list = entBaseinfoService.emailValidata(map);
			String count = String.valueOf(list.get(0).get("counts"));
			if(!count.equals(Constant.VALUE_ZERO)){
				json.setSuccess(true);
			}else {
				json.setSuccess(false);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 验证码输入校验
	 * @return
	 */
	@RequestMapping(value="/valiCodeValidata")
	@ResponseBody
	public Json valiCodeValidata(HttpServletRequest request, String valiCode){
		try {
			Json json = new Json();
			String vali = request.getSession().getAttribute("VALIDATION_CODE").toString().toLowerCase();
			if(valiCode.toLowerCase().equals(vali)){
				json.setSuccess(true);
			}else{
				json.setSuccess(false);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 企业名称唯一性验证
	 * @return
	 */
	@RequestMapping(value="/entNameValidata")
	@ResponseBody
	public Json entNameValidata(String entName){
		try {
			Json json = new Json();
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("status", Constant.VALUE_ONE);
			map.put("entName", entName);
			List<Map<String,Object>> list = entBaseinfoService.emailValidata(map);
			String count = String.valueOf(list.get(0).get("count"));
			if(count.equals(Constant.VALUE_ONE)){
				json.setSuccess(true);
			}else {
				json.setSuccess(false);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 找回密码
	 * @return
	 */
	@RequestMapping({"/backPw"})
	@ResponseBody
	public Json backPw(HttpSession session, String email){
		try {
			Json json = new Json();
			int pw = (int)((Math.random()*9+1)*100000);
			String newPw = String.valueOf(pw);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("email", email);
			//map.put("enable", Constant.VALUE_ZERO);
			map.put("checkMail", Constant.VALUE_ONE);
//			EntUser user = userService.login(map); 
			List<Map<String,Object>> list = baseUserService.cheackmail(map);
			if(list.size() > 0){
				SendHtmlMailUtil.setIndex(Constant.VALUE_ZERO);//获取邮件服务from 的索引			
				while(true){
					int flags = SendHtmlMailUtil.backPw(newPw, email);
					if(flags == 550){
						SendHtmlMailUtil.setIndex("add");//获取邮件服务from 的索引+1
					}else if(flags == 200){
						CompanyUserInfo user = new CompanyUserInfo();
						user.setId(String.valueOf(list.get(0).get("company_user_id")));
						user.setPassword(MD5Util.encrypt(newPw));
						user.setEncodePassword(DESCoderUtil.deCode(newPw));
						userService.updateByPrimaryKeySelective(user);
						json.setObj(email);
						json.setSuccess(true);
						break;
					}else if(flags != 550 ){
						break;
					}
				}
//				int mailStatus = SendHtmlMailUtil.backPw(newPw, email);
//				if(mailStatus == 0){
//					CompanyUserInfo user = new CompanyUserInfo();
//					user.setId(String.valueOf(list.get(0).get("company_user_id")));
//					user.setPassword(MD5Util.encrypt(newPw));
//					userService.updateByPrimaryKeySelective(user);
//					json.setSuccess(true);
//				}
			}else{
				json.setSuccess(false);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 发送短信请求
	 * @param phoneCode
	 * @return
	 */
	@RequestMapping(value="/sendPhoneMsg")
	@ResponseBody
	public Json sendPhoneMsg(HttpServletRequest request, String phone, String code){
		try {
			Json json = new Json();
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			String appkey = pro.getValue(Constant.MOB_STU_APPKEY);
			String url = "";
			String param = "appkey="+ appkey +"&phone="+ phone +"&zone=86";
			
			if(StringUtil.isNotEmpty(code)){
				param = param + "&code=" + code;
				url = pro.getValue(Constant.MOB_STU_URL_CHECK);
			}else{
				url = pro.getValue(Constant.MOB_STU_URL_SEND);
			}
			String result = Constant.VALUE_NAGETIVE;
			try {
				result = HttpRequestUtil.sendPost(url, param);
			} catch (Exception e) {
				result = Constant.VALUE_NAGETIVE;
			}
			
			String status = "0";
			if(StringUtil.isNotEmpty(result)&&!result.equals(Constant.VALUE_NAGETIVE)){
				status = JsonUtil.jsonToObj(result, StautsBean.class).getStatus();
			}
			String msg = "";
			if(status.equals("200")){
				msg = "发送成功";
			}else if(status.equals("458")){
				msg = "手机号码超出当天发送短信的限额";
			}else if(status.equals("463")){
				msg = "手机号码超出当天发送短信的限额";
			}else if(status.equals("467")){
				msg = "请求校验验证码频繁（5分钟校验超过3次）";
			}else if(status.equals("468")){
				msg = "验证码错误";
			}else if(status.equals("477")){
				msg = "亲，手机验证次数过多，请明天再试";
			}else if(status.equals("478")){
				msg = "一个手机号一天只能获取5次";
			}else {
				msg = "错误代码： " + status + "， 请联系管理员";
			}
			json.setMsg(msg);
			json.setObj(status);
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	
}
