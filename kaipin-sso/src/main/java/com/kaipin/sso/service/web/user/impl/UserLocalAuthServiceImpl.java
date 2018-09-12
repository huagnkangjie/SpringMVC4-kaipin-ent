package com.kaipin.sso.service.web.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.common.CookieHelper;
import com.baomidou.kisso.common.IpHelper;

import com.kaipin.sso.Constant;
import com.kaipin.sso.Resource;
import com.kaipin.sso.WebStatus;
import com.kaipin.sso.common.config.impl.SystemConfig;
import com.kaipin.sso.common.message.Messages;
import com.kaipin.sso.constant.Constants;
import com.kaipin.sso.entity.web.company.CompanyBaseUser;
import com.kaipin.sso.entity.web.company.CompanyUserInfo;
import com.kaipin.sso.entity.web.school.SchoolUserInfo;
import com.kaipin.sso.entity.web.user.UserCategory;
import com.kaipin.sso.entity.web.user.UserLocalAuth;
import com.kaipin.sso.exception.ValidateException;
import com.kaipin.sso.manager.UserManager;
import com.kaipin.sso.model.AjaxResultPacket;
import com.kaipin.sso.model.LoginPacket;
import com.kaipin.sso.model.LoginUserInfoPacket;
import com.kaipin.sso.nosql.redis.RedisFactory;
import com.kaipin.sso.nosql.redis.RedisHelper;
import com.kaipin.sso.repository.dao.web.company.ICompanyBaseUserDao;
import com.kaipin.sso.repository.dao.web.university.ISchoolUserInfoDao;
import com.kaipin.sso.repository.dao.web.user.IUserLocalAuthDao;
import com.kaipin.sso.service.web.user.IUserLocalAuthService;
import com.kaipin.sso.util.HttpRequestUtil;
import com.kaipin.sso.util.StringUtils;
import com.kaipin.sso.web.spring.SpringContextHolder;
import com.kaipin.sso.web.token.WebToken;

@Service("userLocalAuthService")
public class UserLocalAuthServiceImpl implements IUserLocalAuthService {

	private static Logger logger = Logger.getLogger(UserLocalAuthServiceImpl.class);

	@Autowired
	private IUserLocalAuthDao userLocalAuthDao;
	@Autowired
	private ICompanyBaseUserDao companyBaseUserDao;
	@Autowired
	private ISchoolUserInfoDao schUserInfoDao;
	
	@Autowired
	private UserManager userManager;

	/***
	 * @see IUserLocalAuthService#login
	 */
	@Override
	public Object login(HttpServletRequest request, HttpServletResponse response, String username, String password)
			throws ValidateException, Exception {

		Map<String, Object> map = new HashMap<>();

		map.put("account", username);

		UserLocalAuth user = userLocalAuthDao.getLoginUser(map);

		String md5Pwd = StringUtils.md5(password);

		String errMessage;
		String uid ;
		String uType;
		
		LoginUserInfoPacket rInfo = null;
		

		if (user == null) {
			throw new ValidateException("" + WebStatus.WEB_R_ERROR, Messages.getString("error.usernameOrPwd"));
		}else{
			uid = user.getId();
			uType = user.getCategoryId();
		}
		

		// 密码错误
		if (!user.getPassword().equals(md5Pwd)) {
			throw new ValidateException("" + WebStatus.WEB_R_ERROR, Messages.getString("error.usernameOrPwd"));
		}
		
		// 用户被禁用
		if (!user.enableStatus()) {

			rInfo=new LoginUserInfoPacket("" + WebStatus.WEB_R_USER_DISABLED, Messages.getString("error.userDisabled.10"), user.getId());
			return rInfo;
//							throw new ValidateException("" + WebStatus.WEB_R_USER_DISABLED, 
//									Messages.getString("error.userDisabled.10"));

		}
		
		//更改最后登录时间
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("last_login_time", System.currentTimeMillis());
		maps.put("id", user.getId());
		userLocalAuthDao.updateUserLoginTime(maps);
		
		//未选择角色
		if (StringUtils.isBlank(user.getCategoryId())) {
			rInfo=new LoginUserInfoPacket("" + WebStatus.WEB_R_NOT_ROLE, Messages.getString("error.usernameNotRole.100"), user.getId());
			return rInfo;
			//			throw new ValidateException("" + WebStatus.WEB_R_NOT_ROLE,  Messages.getString("error.usernameNotRole.100"));

		}else{
			if(user.getCategoryId().equals(Constants.USER_TYPE_STU)){
				rInfo=new LoginUserInfoPacket("" + WebStatus.WEB_R_NO_STU, Messages.getString("error.noStu"), user.getId());
				return rInfo;
			}
		}
		
		//企业用户未填写基本信息
		if (StringUtils.isNotEmpty(user.getCategoryId()) && user.getCategoryId().equals("11")) {
			CompanyBaseUser company = companyBaseUserDao.getCompanyUserById(user.getId());
			if(company == null || checkEntInfo(company)){
				rInfo=new LoginUserInfoPacket("" + WebStatus.WEB_R_ENT_INFO_ERRO, Messages.getString("error.userInfoIsNull"), user.getId());
				return rInfo;
				//throw new ValidateException("" + WebStatus.WEB_R_ENT_INFO_ERRO, user.getId(), Messages.getString("error.userInfoIsNull"));
			}else{
				if(StringUtils.isEmpty(company.getCompanyInfo().getEntName())
						&& StringUtils.isEmpty(company.getCompanyInfo().getEntSimpleName())
						&& StringUtils.isEmpty(company.getCompanyInfo().getOfficeArea())){
					rInfo=new LoginUserInfoPacket("" + WebStatus.WEB_R_ENT_INFO_ERRO, Messages.getString("error.userInfoIsNull"), user.getId());
					return rInfo;
				}
			}
		}else if(StringUtils.isNotEmpty(user.getCategoryId()) && user.getCategoryId().equals("12")){
			SchoolUserInfo schUserInfo = schUserInfoDao.getById(user.getId());
			if(schUserInfo == null){
				rInfo=new LoginUserInfoPacket("" + WebStatus.WEB_R_SCH_INFO_ERRO, Messages.getString("error.userInfoIsNull"), user.getId());
				return rInfo;
			}
		}
		
		// 如果email登陆
		if (StringUtils.validEmail(username)) {
			String redirect_uri = Constants.VALUE_NEGATIVE;//代表表中
			// 邮件未激活
			if (!user.activeEmailStatus()) {
				/*if(user.getCategoryId().equals(Constants.USER_TYPE_ENT)){
					CompanyBaseUser company = companyBaseUserDao.getCompanyUserById(user.getId());
					//检查一下时候资质认证
					List<Map<String,Object>> list = companyBaseUserDao.selectDoc(user.getId());
					
					if(company == null){//公司基本信息为空，直接跳转到基本信息页面
						rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_EMIAL_NOT_ACTIV, Messages.getString("error.userInfoIsNull"), user.getId(), "-1");
					}else{
						//信息不为空，则判断该公司上传资质文件没得
						if(list.size() == 0){
							redirect_uri = Constants.VALUE_ZREO;//没有资质证件
						}else{
							String param = "uid="+user.getId()+"&uType=11";
							HttpRequestUtil.sendGet(SystemConfig.getInstance().getMailUrl(), param);
							redirect_uri = Constants.VALUE_ONE;//有资质证件
						}
						rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_EMIAL_NOT_ACTIV, Messages.getString("error.emailInActive"), user.getId(), company.getCompanyInfo().getId());
					}
				}
				
				rInfo.setRedirect_uri(redirect_uri);
				rInfo.setU_type(user.getCategoryId());*/
				return checkInfo(user.getCategoryId(), uid);
			}else{
//				return checkInfo(user.getCategoryId(), uid);
			}
			
		}

		// 如果手机登陆
		if (StringUtils.validMobile(username)) {

			// 手机未激活
			if (!user.activePhoneStatus()) {
				rInfo=new LoginUserInfoPacket("" + WebStatus.WEB_R_ERROR, Messages.getString("error.mobileInActive"), user.getId());
				return rInfo;
				//throw new ValidateException("" + WebStatus.WEB_R_ERROR,Messages.getString("error.mobileInActive"));

			}

		}
		
		//检查资质认证
		if(!checkDoc(uid, uType)){
			rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_EMIAL_NO_DOC, Messages.getString("error.noDoc"), uid, uType);
			if(uType.equals(Constants.USER_TYPE_ENT)){
				uType = "ent";
			}else if(uType.equals(Constants.USER_TYPE_SCH)){
				uType = "sch";
			}
			rInfo.setU_type(uType);
			return rInfo;
		}


		if(rInfo == null){
			rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_OK, Messages.getString("info.login"), user.getId());
			rInfo.setU_type(user.getCategoryId());
		}
		
		//写cookie
		WebToken token = createWebToken(request, response, user);
		
		rInfo.redirect_uri = token.getRedirect_uri();

		rInfo.uid = token.getUid();
		
		rInfo.group_id=token.getGroup_id();
		
		


		return rInfo;

	}

	private String buildAccessToken(String userType, String uid) {

		return Constant.WEB_ACCESS_TOKEN_PREFIX + Constant.COLON + userType + Constant.COLON + uid;

	}
	
	/**
	 * 用户登录，如果邮箱未激活的操作
	 * @param uType
	 * @param uid
	 * @return
	 */
	public LoginUserInfoPacket checkInfo(String uType, String uid){
		LoginUserInfoPacket rInfo = new LoginUserInfoPacket();
		try {
			String redirect_uri = Constants.VALUE_NEGATIVE;//代表表中
			if(uType.equals(Constants.USER_TYPE_ENT)){
				CompanyBaseUser company = companyBaseUserDao.getCompanyUserById(uid);
				//检查一下时候资质认证
				List<Map<String,Object>> list = companyBaseUserDao.selectDoc(uid);
				
				if(company == null){//公司基本信息为空，直接跳转到基本信息页面
					rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_EMIAL_NOT_ACTIV, Messages.getString("error.userInfoIsNull"), uid, "-1");
				}else{
					String errorTip ="";
					//信息不为空，则判断该公司上传资质文件没得
					if(list.size() == 0){
						redirect_uri = Constants.VALUE_ZREO;//没有资质证件
						errorTip = "error.noDoc";
					}else{
						String param = "uid="+uid+"&uType=11";
						HttpRequestUtil.sendGet(SystemConfig.getInstance().getMailUrl(), param);
						redirect_uri = Constants.VALUE_ONE;//有资质证件
						errorTip = "error.emailInActive";
					}
					rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_EMIAL_NOT_ACTIV, Messages.getString(errorTip), uid, company.getCompanyId());
				}
				uType = "ent";
			}else if(uType.equals(Constants.USER_TYPE_SCH)){
				SchoolUserInfo info = schUserInfoDao.getById(uid);
				
				List<Map<String,Object>> list = schUserInfoDao.selectDoc(uid);
				
				if(info == null){//公司基本信息为空，直接跳转到基本信息页面
					rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_EMIAL_NOT_ACTIV, Messages.getString("error.userInfoIsNull"), uid, "-1");
				}else{
					String errorTip ="";
					//信息不为空，则判断该公司上传资质文件没得
					if(list.size() == 0){
						redirect_uri = Constants.VALUE_ZREO;//没有资质证件
						errorTip = "error.noDoc";
					}else{
						String param = "uid="+uid+"&uType=12";
						HttpRequestUtil.sendGet(SystemConfig.getInstance().getMailUrl(), param);
						redirect_uri = Constants.VALUE_ONE;//有资质证件
						errorTip = "error.emailInActive";
					}
					rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_EMIAL_NOT_ACTIV, Messages.getString(errorTip), uid, info.getSchoolId());
				}
				uType = "sch";
			}else if(uType.equals(Constants.USER_TYPE_SCH)){
				rInfo = new LoginUserInfoPacket("" + WebStatus.WEB_R_EMIAL_NOT_ACTIV, Messages.getString("error.noStu"), uid, uid);
				uType = "stu";
			}
			
			rInfo.setRedirect_uri(redirect_uri);
			rInfo.setU_type(uType);
			return rInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return rInfo;
		}
	}

	/**
	 * 创建token 及记录客户cookies
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	private WebToken createWebToken(HttpServletRequest request, HttpServletResponse response, UserLocalAuth user) {

		WebToken token = new WebToken();
		UserCategory userCategory = user.getUserCategory();

		String groupId = userManager.getUserGroupId(user.getId(), userCategory.getUserType());

		token.setUid(user.getId());

		token.setExpires_in(SSOHelper.getKissoService().getConfig().getCacheExpires());

		token.setType(userCategory.getUserType());

		token.setApp(Constant.WEB_ACCESS_TOKEN_PREFIX);

		token.setRedirect_uri(userCategory.getRedirectUri());
		token.setGroup_id(groupId);
		token.setIp(IpHelper.getIpAddr(request));

		// 存放token
		SSOHelper.setSSOCookie(request, response, token, true);

		SSOHelper.addCookieRWNoSecure(response, Resource.UID, user.getId());

		SSOHelper.addCookieRWNoSecure(response, Resource.UTYPE, userCategory.getUserType());

		SSOHelper.addCookieRWNoSecure(response, Resource.CLIENT_TYPE, Constant.WEB_ACCESS_TOKEN_PREFIX);

		SSOHelper.addCookieRWNoSecure(response, Resource.GROUP_ID, groupId);

		return token;
	}

	@Override
	public Object logout(HttpServletRequest request, HttpServletResponse response) throws ValidateException, Exception {

		String domain = SSOHelper.getKissoService().getConfig().getCookieDomain();

		String path = SSOHelper.getKissoService().getConfig().getCookiePath();

		String userType = "";
		String uid = "";

		Cookie uTypeCookie = CookieHelper.findCookieByName(request, Resource.UID);

		Cookie uidCookie = CookieHelper.findCookieByName(request, Resource.UTYPE);

		if (uTypeCookie != null)
			userType = uidCookie.getValue();

		if (uidCookie != null)
			uid = uidCookie.getValue();

		SSOHelper.logout(request, response);

		CookieHelper.clearAllCookie(request, response, domain, path);

		RedisHelper redisHelper = null;

		if (!userType.equals("") && !uid.equals("")) {
			try {
				redisHelper = RedisFactory.getRedisHelper();

				String key = buildAccessToken(userType, uid);

				redisHelper.del(key);

			} catch (Exception e) {

			}

			finally {
				if (redisHelper != null)
					redisHelper.release();
			}

		}

		return null;
	}
/***
 * @see IUserLocalAuthService#loginCheck
 */
	@Override
	public Object loginCheck(HttpServletRequest request, HttpServletResponse response)
			throws ValidateException, Exception {

		Token token = SSOHelper.getToken(request);

		AjaxResultPacket result = new AjaxResultPacket();

		if (token == null) {

			result.code = WebStatus.WEB_R_TOKEN_NOT_FOUND + "";

			result.message = Messages.getString("error.tokenNotFound.150");

			WebToken webToken = new WebToken();

			webToken.setTime(0);

			result.data =webToken;

		} else {

			result.code = WebStatus.WEB_R_OK + "";

			result.message = Messages.getString("info.ok");

			result.data = token;
		}

		return result;
	}
	
	@Override
	public UserLocalAuth selectByPramiKey(String id) {
		return userLocalAuthDao.selectByPramiKey(id);
	}

	@Override
	public Object rCookie(HttpServletRequest request, HttpServletResponse response, UserLocalAuth user) {
		WebToken token = createWebToken(request, response, user);
		return token;
	}

	@Override
	public boolean updateUserLoginTime(Map<String, Object> map) {
		return userLocalAuthDao.updateUserLoginTime(map);
	}

	/**
	 * 检查资质是否有资质认证,如果有返回true
	 * @param uid
	 * @param uType
	 * @return
	 */
	public boolean checkDoc(String uid, String uType){
		boolean flag = false;
		try {
			List<Map<String,Object>> list = new ArrayList<>();
			if(uType.equals(Constants.USER_TYPE_ENT)){
				list = companyBaseUserDao.selectDoc(uid);
				
			}else if(uType.equals(Constants.USER_TYPE_SCH)){
				list = schUserInfoDao.selectDoc(uid);
			}
			if(list.size() > 0){
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			return flag;
		}
	}
	
	/**
	 * 检查企业基本信息是否完善,如果未完善，返回true
	 * @param companyBaseUser
	 * @return
	 */
	public boolean checkEntInfo(CompanyBaseUser companyBaseUser){
		boolean flag = false;
		try {
			String surName = companyBaseUser.getCompanyUserInfo().getSurname();
			String misSurName = companyBaseUser.getCompanyUserInfo().getMissSurname();
			String officeArea = companyBaseUser.getCompanyInfo().getOfficeArea();
			String entName = companyBaseUser.getCompanyInfo().getEntName();
			String entSimpelName = companyBaseUser.getCompanyInfo().getEntSimpleName();
			
			if(StringUtils.isEmpty(surName) && StringUtils.isEmpty(misSurName)){
				flag = true;
			}
			
//			if(StringUtils.isEmpty(officeArea)){
//				flag = true;
//			}
			
			if(StringUtils.isEmpty(entName)){
				flag = true;
			}
			
			if(StringUtils.isEmpty(entSimpelName)){
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			return flag;
		}
	}
}
