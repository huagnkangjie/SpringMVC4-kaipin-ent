package com.web.regedit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.constants.AppSearchConstant;
import com.common.constants.Constant;
import com.common.pojo.Json;
import com.model.company.CompanyInfo;
import com.model.company.CompanyUserInfo;
import com.model.sch.SchoolInfo;
import com.model.sch.SchoolInfoLink;
import com.model.sch.SchoolUserInfo;
import com.model.stu.ResumeInfo;
import com.model.stu.StuUser;
import com.model.user.User;
import com.model.user.UserLocalauth;
import com.pojo.StautsBean;
import com.service.common.ICommonCodeService;
import com.service.company.ICompanyInfoService;
import com.service.company.ICompanyUserInfoService;
import com.service.company.IEntBaseUserService;
import com.service.sch.ISchoolBaseUserService;
import com.service.sch.ISchoolInfoLinkService;
import com.service.sch.ISchoolUserInfoService;
import com.service.stu.IResumeInfoService;
import com.service.stu.IStuBaseUserService;
import com.service.stu.IStuUserService;
import com.service.user.IUserLocalauthService;
import com.service.user.IUserService;
import com.util.DESCoderUtil;
import com.util.HttpPostUtil;
import com.util.HttpRequestUtil;
import com.util.InvitationCodeUtil;
import com.util.JsonUtil;
import com.util.LogUtil;
import com.util.LuceneUtil;
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
@RequestMapping("/regedit")
public class RegeditController {
	
	Logger log = Logger.getLogger(RegeditController.class.getName());
	
	@Autowired
	private IUserService userService;//全局用户
	@Autowired
	private IUserLocalauthService localUserService;//用户登录表
	@Autowired
	private ICommonCodeService commonCodeService;//公用码表服务
	@Autowired
	private IEntBaseUserService entBaseUserService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private ICompanyUserInfoService companyUserInfoService;
	@Autowired
	private IStuUserService stuUserService;
	@Autowired
	private ISchoolBaseUserService schBaserService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	@Autowired
	private ISchoolUserInfoService schUserInfoService;
	@Autowired
	private IResumeInfoService resumeInfoService;
	@Autowired
	private IStuBaseUserService stuBaseService;
	
	
	/**
	 * 注册页面初始化
	 * 找回密码初始化页面
	 * @return
	 */
	@RequestMapping({"/init"})
	public String init(HttpServletRequest request, String oper,String userId, String orgId, UserLocalauth user, Model model){
		try {
			if(StringUtil.isEmpty(userId) && user != null){
				if(StringUtil.isNotEmpty(user.getId()))
				userId = user.getId();
			}
			if(StringUtil.isNotEmpty(userId)){
				user = localUserService.selectByPrimaryKey(userId);
			}
			model.addAttribute("userId", userId);//用户id
			model.addAttribute("orgId", orgId);//企业  学习  学生   基本信息id
			model.addAttribute("localUser", user);
			
			CompanyInfo company = new CompanyInfo();
			CompanyUserInfo companyUserInfo = new CompanyUserInfo();
			if(StringUtil.isNotEmpty(oper)){
				if(oper.startsWith("regedit_basic")){
					model.addAttribute("proviceList", initList());
					//如果企业信息为完善，先查询一次企业信息，老数据
					if(oper.equals("regedit_basic_ent")){
						orgId = user.getOrganizationId();
						company = companyInfoService.selectByPrimaryKey(orgId);
						companyUserInfo = companyUserInfoService.selectByPrimaryKey(userId);
					}
				}
				model.addAttribute("company", company);
				model.addAttribute("companyUserInfo", companyUserInfo);
				return "/regedit/" + oper;
			}
			
			return "/regedit/regedit";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 审核页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value=("/checkPage"))
	public String checkPage(HttpServletRequest request, Model model, 
			String oper, String userId, String orgId, String uploadAgin){
		try {
			String page = "";
			
			if(oper.equals(Constant.USER_TYPE_ENT)){
				page = "certificate_check_ent";
			}
			model.addAttribute("userType", oper);
			model.addAttribute("uploadAgin", uploadAgin);
			model.addAttribute("userId", userId);
			model.addAttribute("orgId", orgId);
			return "/regedit/" + page;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 初始化list
	 * @return
	 */
	public List<Map<String,Object>> initList(){
		List<Map<String,Object>> list = null;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sql", "select * from comm_location where parent_code = '489'");
			list = commonCodeService.getCodeList(map);
			return list;
		} catch (Exception e) {
			return list;
		}
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping({"/register"})
	@ResponseBody
	public Json register(HttpSession session, String oper, UserLocalauth user){
		Json json = new Json();
		try {
			String userId = UuidUtil.getUUID();
			String password = user.getPassword();
			user.setPassword(MD5Util.encrypt(password));
			user.setEncodePassword(DESCoderUtil.deCode(password));
			user.setIsActivePhone(Byte.valueOf(Constant.VALUE_ONE));
			user.setId(userId);
			user.setInvitationCode(InvitationCodeUtil.createRandCode());
			user.setEmail(user.getEmail().toLowerCase());
			user.setCreateTime(TimeUtil.currentTimeMillis());
			int i = localUserService.insertSelective(user);
			if(i == 1){
				json.setSuccess(true);
//				//发送邮件
//				SendHtmlMailUtil.setIndex(Constant.VALUE_ZERO);//获取邮件服务from 的索引			
//				while(true){
//					if(StringUtil.isEmpty(user.getEmail())){
//						break;
//					}
//					int flags = SendHtmlMailUtil.sendCheakMail(userId, user.getEmail());
//					if(flags == 550){
//						SendHtmlMailUtil.setIndex("add");//获取邮件服务from 的索引+1
//					}else if(flags == 200){
						Map<String,String> map = new HashMap<String, String>();
						map.put("userId", userId);
						json.setObj(map);
//						break;
//					}else if(flags != 550 ){
//						break;
//					}
//				}
			}
			
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 注册基本信息
	 */
	@RequestMapping({"/regeditBasic"})
	@ResponseBody
	public Json regeditBasic(HttpSession session, String oper, String userId, CompanyUserInfo entUser,
			CompanyInfo entInfo, StuUser stuUser, String schoolId, SchoolUserInfo schUserInfo,
			SchoolInfoLink schLinkInfo){
		Json json = new Json();
		int flag = 0;
		try {
			long time = TimeUtil.currentTimeMillis();
			Map<String,Object> map = new HashMap<String,Object>();
			String orgId = UuidUtil.getUUID();
			if(entInfo != null){
				if(StringUtil.isNotEmpty(entInfo.getId())){
					orgId = entInfo.getId();
				}
			}
			map.put("orgId", orgId);
			map.put("userId", userId);
			map.put("schoolId", schoolId);
			if(oper.equals(Constant.USER_TYPE_ENT)){
				CompanyInfo rInfo = new CompanyInfo();
				CompanyUserInfo rUserInfo = companyUserInfoService.selectByPrimaryKey(userId);
				List<Map<String,Object>> list = entBaseUserService.getBaseEntUser(map);
				if(list.size() == 0 && rUserInfo == null){
					entUser.setId(userId);
					entInfo.setId(orgId);
					
					entUser.setCreateTime(time);
					entUser.setLastUpdatedTime(time);
					
					entInfo.setCreateTime(time);
					entInfo.setLastUpdatedTime(time);
					
					flag = createEnt(entUser, entInfo);
				}else{
					Object o = list.get(0).get("company_id");
					if(o != null){
						orgId = String.valueOf(o);
					}
					rInfo.setId(orgId);
					rInfo.setEntName(entInfo.getEntName());
					rInfo.setEntSimpleName(entInfo.getEntSimpleName());
					rInfo.setOfficeArea(entInfo.getOfficeArea());
					
					rInfo.setCreateTime(time);
					rInfo.setLastUpdatedTime(time);
					
					int i = companyInfoService.updateByPrimaryKeySelective(rInfo);
					
					rUserInfo.setId(userId);
					rUserInfo.setSurname(entUser.getSurname());
					rUserInfo.setMissSurname(entUser.getMissSurname());
					rUserInfo.setCreateTime(time);
					rUserInfo.setLastUpdatedTime(time);
					int j = companyUserInfoService.updateByPrimaryKeySelective(rUserInfo);
					
					if(i == 1 && j == 1){
						flag = 1;
					}
				}
				
				//更新用户组织id
				UserLocalauth localUser = localUserService.selectByPrimaryKey(userId);
				localUser.setOrganizationId(orgId);
				localUserService.updateByPrimaryKeySelective(localUser);
				
			}else if(oper.equals(Constant.USER_TYPE_STU)){
				
				StuUser rUser = stuUserService.selectByPrimaryKey(userId);
				UserLocalauth user = localUserService.selectByPrimaryKey(userId);
				
				if(rUser == null){
					stuUser.setId(userId);
					
					stuUser.setCreateTime(time);
					stuUser.setLastUpdatedTime(time);
					
					flag = createStu(stuUser);
				}else{
					rUser.setId(userId);
					rUser.setSurname(stuUser.getSurname());
					rUser.setMissSurname(stuUser.getMissSurname());
					rUser.setNickName(stuUser.getSurname() +","+ stuUser.getMissSurname());//昵称
					rUser.setSchoolCode(stuUser.getSchoolCode());
					rUser.setMajorCode(stuUser.getMajorCode());
					rUser.setLocationCode(stuUser.getLocationCode());
					
					rUser.setCreateTime(time);
					rUser.setLastUpdatedTime(time);
					
					flag = stuUserService.updateByPrimaryKeySelective(rUser);
					
				}
				
				//新增一个简历
				ResumeInfo resume = new ResumeInfo();
				String resumeId =  UuidUtil.getUUID();
				resume.setId(resumeId);
				resume.setResumeName("简历_" + TimeUtil.currentTimeMillis());
				resume.setStuUserId(userId);
				resume.setMissSurname(stuUser.getMissSurname());
				resume.setSurname(stuUser.getSurname());
				resume.setLocationCode(stuUser.getLocationCode());
				resume.setPhone(user.getPhone());
				resume.setEmail(user.getEmail());
				resume.setCreateTime(TimeUtil.currentTimeMillis());
				
				resumeInfoService.deleteByStuUserId(userId);
				resumeInfoService.insertSelective(resume);
				
				//添加教育背景
				Map<String, Object> mapEdu = new HashMap<String, Object>();
				mapEdu.put("id", UuidUtil.getUUID());
				mapEdu.put("start_time", "");
				mapEdu.put("end_time", "");
				mapEdu.put("education_code", stuUser.getEducationCode());
				mapEdu.put("school", stuUser.getSchoolCode());
				mapEdu.put("major", stuUser.getMajorCode());
				mapEdu.put("create_time", TimeUtil.currentTimeMillis());
				mapEdu.put("resume_id", resumeId);
				
				mapEdu.put("stuId", stuUser.getId());
				
				//删除教育背景
				stuBaseService.delEdu(mapEdu);
				stuBaseService.insertEdu(mapEdu);
				
				//创建索引
				//createIndex(userId, rUser);
				LuceneUtil.luceneOpt(userId, Constant.VALUE_THREE, Constant.VALUE_ONE);
				
				//更新用户组织id
				UserLocalauth localUser = localUserService.selectByPrimaryKey(userId);
				localUser.setOrganizationId(userId);
				localUserService.updateByPrimaryKeySelective(localUser);
				
			}else if(oper.equals(Constant.USER_TYPE_SCH)){
					schUserInfo.setId(userId);
					schUserInfo.setSchoolId(orgId);
					schLinkInfo.setId(orgId);
					
					schUserInfo.setCreateTime(time);
					schUserInfo.setLastUpdatedTime(time);
					
					schLinkInfo.setCreateTime(time);
					
					flag = createSch(schUserInfo, schLinkInfo);
					
					//更新用户组织id
					UserLocalauth localUser = localUserService.selectByPrimaryKey(userId);
					localUser.setOrganizationId(orgId);
					localUserService.updateByPrimaryKeySelective(localUser);
			}
			if(flag == 1){
				json.setObj(map);
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	public void createIndex(String userId, StuUser user){
		try {
			Map<String, Object> mapLunece = new HashMap<String, Object>();
			String luneceTaskId = UuidUtil.getUUID();
			mapLunece.put("id", luneceTaskId);
			mapLunece.put("obj_id", userId);//资源id
			mapLunece.put("obj_type", 3);//对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
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
				param.put("id", userId);
				param.put("surname", user.getSurname());
				param.put("miss_surname", user.getMissSurname());
				param.put("last_updated_time", user.getCreateTime());
				
				String s = HttpPostUtil.doPost(pro.getValue(AppSearchConstant.SEARCH_URL) 
						+"/lucene"+ AppSearchConstant.APP_SEARCH_STU_CREATE + luneceTaskId, param);
				System.out.println("stu 索引创建信息： " + s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int createEnt(CompanyUserInfo entUser, CompanyInfo entInfo){
		try {
			return entBaseUserService.createEntUser(entUser, entInfo);
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	public int createStu(StuUser stuUser){
		try {
			return stuUserService.insertSelective(stuUser);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int createSch(SchoolUserInfo schUserInfo, SchoolInfoLink schLinkInfo){
		try {
			return schBaserService.createSchUser(schUserInfo, schLinkInfo);
		} catch (Exception e) {
			return 0;
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
			String licenceImg = "";
			String idCardImg = "";
			String fail = "";
			if(StringUtil.isEmpty(companyId) && StringUtil.isEmpty(userId)){
				fail = "fail";
				model.addAttribute("fail", fail);
			}else{
				
				if(StringUtil.isNotEmpty(userId)){
					//user = userService.selectByPrimaryKey(userId);
				}
				if(StringUtil.isNotEmpty(companyId)){
					//info = companyInfoService.selectByPrimaryKey(companyId);
				}
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("company_id", companyId);
				List<Map<String,Object>> list = null;
//						baseService.getList("company_info_document", map, "");
				if(list.size() > 0){
					Object OLicence = list.get(0).get("business_license_path");
					Object OIdCard = list.get(0).get("id_card_path");
					if(OLicence != null){
						licenceImg = String.valueOf(OLicence);
					}
					if(OIdCard != null){
						idCardImg = String.valueOf(OIdCard);
					}
				}
			}
			model.addAttribute("user", user);
			model.addAttribute("status", status);
			model.addAttribute("info", info);
			model.addAttribute("licenceImg", licenceImg);
			model.addAttribute("idCardImg", idCardImg);
			model.addAttribute("checkType", 0);//审核中
			return "/ent/regedit/certificate_check_ent";
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
	public String certificate(HttpSession session,Model model, String oper, 
			String target, String companyId, String userId, String checkType){
		try {
			String uploadAgin = "0";
			model.addAttribute("success", oper);
			model.addAttribute("userId", userId);
			model.addAttribute("companyId", companyId);
			User user = new User();
			String entName = "企业名称";
			String entSimpleName = "企业简称";
			String status = "0";
			if(StringUtil.isEmpty(target)){
				if(user != null || StringUtil.isNotEmpty(companyId)){
					String entId = "";
					if(user != null){
						//entId = user.getCompanyId();
					}else {
						entId = companyId;
					}
					CompanyInfo info = null;
//					CompanyInfo info = companyInfoService.selectByPrimaryKey(entId);
					String entNameTemp = info.getEntName();
					if(StringUtil.isNotEmpty(entNameTemp)){
						if(!entNameTemp.equals("null") && entNameTemp.length() > 0){
							entName = entNameTemp;
						}
					}
				}
				model.addAttribute("entName", entName);
				return "/ent/regedit/certificate_index_ent";
			}else if(target.equals("certificate")){//从首页点击进行审核
				if(user != null){
					entName = "";
					entSimpleName = "";
					CompanyInfo info = null;
//					CompanyInfo info = companyInfoService.selectByPrimaryKey(user.getCompanyId());
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
					
//					userId = user.getId();
//					companyId = user.getCompanyId();
					
				}else{
					entName = "";
					entSimpleName = "";
//					user = new EntUser();
//					user.setCompanyId(companyId);
				}
				if(StringUtil.isNotEmpty(oper)){
					if(oper.equals("uploadAgin")){
						uploadAgin = "uploadAgin";
					}
				}
				model.addAttribute("userId", userId);
				model.addAttribute("companyId", companyId);
				
				model.addAttribute("uploadAgin", uploadAgin);
				model.addAttribute("status", status);
				model.addAttribute("entName", entName);
				model.addAttribute("entSimpleName", entSimpleName);
				//session.setAttribute(Constant.USER, user);
				return "/ent/regedit/certificate_ent";
			}else if(target.equals("certificate_show")){//查看当前审核的具体情况
				CompanyInfo info = new CompanyInfo();
				List<Map<String,Object>> feedbackList = null;
				String licenceImg = "";
				String idCardImg = "";
				if(user != null){
//					Map<String,Object> map = new HashMap<String,Object>();
//					map.put("company_id",user.getCompanyId());
//					List<Map<String,Object>> list = baseService.getList(ConstantTables.COMPANY_DOC, map, "");
//					if(list.size() > 0){
//						info = companyInfoService.selectByPrimaryKey(user.getCompanyId());
//						licenceImg = String.valueOf(list.get(0).get("business_license_path"));
//						idCardImg = String.valueOf(list.get(0).get("id_card_path"));
//						
//						//如果是审核不通过，则查找不通过的原因
//						CompanyInfo infos = companyInfoService.selectByPrimaryKey(user.getCompanyId());
//						String verifyStatus = String.valueOf(infos.getVerifyStatus());
//						String feedbackId = infos.getFeedbackId();
//						if(StringUtil.isNotEmpty(verifyStatus) 
//								&& StringUtil.isNotEmpty(feedbackId)
//								&& verifyStatus.equals(Constant.VALUE_ONE) ){
//							map.clear();
//							map.put("feedbackId", feedbackId);
//							feedbackList = baseUserService.getFeedbackList(map);
//						}
//					}
				}
				CompanyUserInfo userInfo = null;
//				CompanyUserInfo userInfo = userService.selectByPrimaryKey(user.getId());
//				user.setUserName(userInfo.getUserName());
				
				model.addAttribute("status", 1);//返回企业首页
				model.addAttribute("info", info);
				model.addAttribute("user", userInfo);
				
				model.addAttribute("licenceImg", licenceImg);
				model.addAttribute("idCardImg", idCardImg);
				model.addAttribute("checkType", checkType);
				
				model.addAttribute("feedbackList", feedbackList);//反馈信息
				
				return "/ent/regedit/certificate_check_ent";
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
	 * 发送邮件
	 * @param request
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value=("/sendEmail"))
	@ResponseBody
	public Json sendEmail(HttpServletRequest request, Model model, String userId){
		Json json = new Json();
		try {
			int flags = 0;//默认状态
			if(StringUtil.isNotEmpty(userId)){
				UserLocalauth user = localUserService.selectByPrimaryKey(userId);
				if(user != null && StringUtil.isNotEmpty(user.getEmail())){
					//发送邮件
					SendHtmlMailUtil.setIndex(Constant.VALUE_ZERO);//获取邮件服务from 的索引			
					while(true){
						if(StringUtil.isEmpty(user.getEmail())){
							break;
						}
						flags = SendHtmlMailUtil.sendCheakMail(userId, user.getEmail());
						if(flags == 550){
							SendHtmlMailUtil.setIndex("add");//获取邮件服务from 的索引+1
						}else if(flags == 200){
							json.setSuccess(true);
							break;
						}else if(flags != 550 ){
							break;
						}
					}
				}
			}
			Map<String,String> map = new HashMap<String, String>();
			map.put("userId", userId);
			json.setObj(map);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	
	/**
	 * 验证邮箱发过来的验证
	 * @return
	 */
	@RequestMapping(value="/checkmail") 
	public String cheackmail(HttpServletRequest request, String uuid, String email, Model model){
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("id", uuid);
			map.put("email", email);
			//172800000 两天时间的时间戳
			List<Map<String,Object>> list = userService.cheackmail(map);
			if(list.size() == 1) {
				UserLocalauth user = new UserLocalauth();
				user.setId(uuid);
				user.setIsActiveEmail(Byte.valueOf(Constant.VALUE_ONE));
				localUserService.updateByPrimaryKeySelective(user);
				
				model.addAttribute("userId", uuid);
				model.addAttribute("email", email);
				
				return "/regedit/cheakmail_success";
			}else{
				return "/regedit/cheakmail_fail";
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
			List<Map<String,Object>> list = null;
//			List<Map<String,Object>> list = baseUserService.againMail(map);
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
	public Json reSendMail(String uId, String email){
		try {
			Json json = new Json();
			CompanyUserInfo userInfo = null;
			if(StringUtil.isNotEmpty(uId) && StringUtil.isNotEmpty(email)){
				UserLocalauth user = new UserLocalauth();
				user.setId(uId);
				user.setEmail(email);
				int i = localUserService.updateByPrimaryKeySelective(user);
				if(i == 1){
					SendHtmlMailUtil.setIndex(Constant.VALUE_ZERO);//获取邮件服务from 的索引			
					while(true){
						int flags = SendHtmlMailUtil.sendCheakMail(uId, email);
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
			List<Map<String,Object>> list = userService.validataPhone(map);
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
			map.put("email", email);
			List<Map<String,Object>> list = userService.emailValidata(map);
			if(list.size() != 0){
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
	 * 学校唯一性验证
	 * @return
	 */
	@RequestMapping(value="/schoolValidata")
	@ResponseBody
	public Json schoolValidata(String schoolId){
		try {
			Json json = new Json();
			SchoolInfoLink link = schLinkService.selectByPrimaryKey(schoolId);
			if(link == null){
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
	 * 邀请码唯一性验证
	 * @return
	 */
	@RequestMapping(value="/validataInviteCode")
	@ResponseBody
	public Json validataInviteCode(String inviteCode){
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("inviteCode", inviteCode);
			List<Map<String,Object>> list = userService.validataInviteCode(map);
			if(list.size() == 1){
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
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
			List<Map<String,Object>> list = null;
//			List<Map<String,Object>> list = entBaseinfoService.emailValidata(map);
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
		Json json = new Json();
		try {
			int pw = (int)((Math.random()*9+1)*100000);
			String newPw = String.valueOf(pw);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("email", email);
			List<Map<String,Object>> list = userService.emailValidata(map);
			if(list.size() == 1){
				SendHtmlMailUtil.setIndex(Constant.VALUE_ZERO);//获取邮件服务from 的索引			
				while(true){
					int flags = SendHtmlMailUtil.backPw(newPw, email);
					if(flags == 550){
						SendHtmlMailUtil.setIndex("add");//获取邮件服务from 的索引+1
					}else if(flags == 200){
						UserLocalauth user = new UserLocalauth();
						user.setId(String.valueOf(list.get(0).get("id")));
						user.setPassword(MD5Util.encrypt(newPw));
						user.setEncodePassword(DESCoderUtil.deCode(newPw));
						localUserService.updateByPrimaryKeySelective(user);
						json.setObj(email);
						json.setSuccess(true);
						break;
					}else if(flags != 550 ){
						break;
					}
				}
				int mailStatus = SendHtmlMailUtil.backPw(newPw, email);
				if(mailStatus == 0){
					UserLocalauth user = new UserLocalauth();
					user.setId(String.valueOf(list.get(0).get("id")));
					user.setPassword(MD5Util.encrypt(newPw));
					localUserService.updateByPrimaryKeySelective(user);
					json.setSuccess(true);
				}
			}else{
				json.setSuccess(false);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
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
	
	/**
	 * 选择角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/chooseRole")
	@ResponseBody
	public Json chooseRole(HttpServletRequest request, String type, String userId){
		Json json = new Json();
		try {
			if(StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(userId)){
				UserLocalauth user = localUserService.selectByPrimaryKey(userId);
				user.setCategoryId(type);
				if(type.equals("10")){
					user.setLastLoginTime(TimeUtil.currentTimeMillis());
				}
				int i = localUserService.updateByPrimaryKeySelective(user);
				if(i == 1){
					json.setSuccess(true);
				}
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 学生登录二维码页面
	 */
	@RequestMapping("/stu-qr-code")
	public String stuQrCode(HttpServletRequest request, Model model){
		return "/regedit/stu_qr_code";
		
	}
	
}
