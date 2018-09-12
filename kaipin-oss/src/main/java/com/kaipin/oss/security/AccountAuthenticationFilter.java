package com.kaipin.oss.security;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.oss.common.web.CookieUtils;
import com.kaipin.oss.common.web.RequestUtils;
import com.kaipin.oss.common.web.session.SessionProvider;
import com.kaipin.oss.exception.security.CaptchaErrorException;
import com.kaipin.oss.exception.security.CaptchaRequiredException;
import com.kaipin.oss.exception.security.DeleteException;
import com.kaipin.oss.exception.security.DisabledException;
import com.kaipin.oss.model.platform.PlatformUser;
import com.kaipin.oss.service.platform.PlatformUserService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 *  自定义登录认证filter
 */
public class AccountAuthenticationFilter extends FormAuthenticationFilter {
	
	private Logger logger = LoggerFactory.getLogger(AccountAuthenticationFilter.class);
	
	public static final String COOKIE_ERROR_REMAINING = "_error_remaining";
	/**
	 * 验证码名称
	 */
	public static final String CAPTCHA_PARAM = "captcha";
	/**
	 * 返回URL
	 */
	public static final String RETURN_URL = "returnUrl";
	/**
	 * 登录错误地址
	 */
	public static final String FAILURE_URL = "failureUrl";
	
	
	@Autowired
	private SessionProvider session;
	
 
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	
	 
	
	@Autowired
	private PlatformUserService platformUserService;
	
		private String adminPrefix;
		private String adminIndex;
		private String adminLogin;

	protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			String msg = "create AuthenticationToken error";
			throw new IllegalStateException(msg);
		}
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String username = (String) token.getPrincipal();
		
 
		String failureUrl = req.getParameter(FAILURE_URL);
		//验证码校验
	 
			String captcha = request.getParameter(CAPTCHA_PARAM).toUpperCase();
			
			if (captcha != null) {
				if (!imageCaptchaService.validateResponseForID(session.getSessionId(req, res), captcha)) {
					return onLoginFailure(token,failureUrl, new CaptchaErrorException(), request, response);
				}
			} else {
				return onLoginFailure(token,failureUrl,new CaptchaRequiredException(),request, response);
			}
		 
			
			//检验用户
			PlatformUser  user = platformUserService.findByUsername(username);
			
		
		
		if(user!=null){
			if(isDisabled(user)){
				return onLoginFailure(token,failureUrl, new DisabledException(),request, response);
			}
			if( isDelete (user)){
				return onLoginFailure(token,failureUrl, new DeleteException(),request, response);
			}
		}
		try {
			Subject subject = getSubject(request, response);
			
			
	
			
			subject.login(token);
			
			
			
			
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			//e.printStackTrace();
			return onLoginFailure(token,failureUrl,  e, request, response);
		}
	}

	public boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		boolean isAllowed = isAccessAllowed(request, response, mappedValue);
		//登录跳转
		if (isAllowed && isLoginRequest(request, response)) {
			try {
				issueSuccessRedirect(request, response);
			} catch (Exception e) {
				logger.error("", e);
			}
			return false;
		}
		
		return isAllowed || onAccessDenied(request, response, mappedValue);
	}
	

	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String successUrl = req.getParameter(RETURN_URL);
		if (StringUtils.isBlank(successUrl)) {
	
				// 后台直接返回首页
				successUrl = getAdminIndex();
				// 清除SavedRequest
				WebUtils.getAndClearSavedRequest(request);
				
				WebUtils.issueRedirect(request, response, successUrl, null,true);
				return;
			} else {
				successUrl = getSuccessUrl();
			}
		
		WebUtils.redirectToSavedRequest(req, res, successUrl);
	}

	protected boolean isLoginRequest(ServletRequest req, ServletResponse resp) {
		return pathsMatch(getLoginUrl(), req)|| pathsMatch(getAdminLogin(), req);
	}

	/**
	 * 登录成功
	 */
	protected boolean onLoginSuccess(AuthenticationToken token,boolean adminLogin,Subject subject,
			ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String username = (String) subject.getPrincipal();
		

 
		loginCookie(username, req, res);
		
		

		return super.onLoginSuccess(token, subject, request, response);
	}

	/**
	 * 登录失败
	 */
	private boolean onLoginFailure(AuthenticationToken token,String failureUrl, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		String username = (String) token.getPrincipal();
		HttpServletRequest req = (HttpServletRequest) request;
		String ip = RequestUtils.getIpAddr(req);

		
		return onLoginFailure(failureUrl,token, e, request, response);
	}
	
	private boolean onLoginFailure(String failureUrl,AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		String className = e.getClass().getName();
		
        request.setAttribute(getFailureKeyAttribute(), className);
        
        if(failureUrl!=null||StringUtils.isNotBlank(failureUrl)){
        	try {
    			request.getRequestDispatcher(failureUrl).forward(request, response);
    		}  catch (Exception e1) {
    			//e1.printStackTrace();
    		}
        }
        return true;
	}
	
	private void loginCookie(String username,HttpServletRequest request,HttpServletResponse response){
		String domain = request.getServerName();
		if (domain.indexOf(".") > -1) {
			domain= domain.substring(domain.indexOf(".") + 1);
		}
		CookieUtils.addCookie(request, response,  "JSESSIONID",  session.getSessionId(request, response), null, domain,"/");
		try {
			//中文乱码
			CookieUtils.addCookie(request, response,   "username",  URLEncoder.encode(username,"utf-8"), null, domain,"/");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
	}
	

	//用户禁用返回true 未查找到用户或者非禁用返回false
	private boolean isDisabled(PlatformUser user){
		if(user.enabledType()){
			return true;
		}else{
			return false;
		}
	}
	
	//是否为删除用户
	private boolean isDelete(PlatformUser user){
	 
		if(user.deleteMark()){
			return true;
		}else{
			return false;
		}
	}
	




	public String getAdminPrefix() {
		return adminPrefix;
	}

	public void setAdminPrefix(String adminPrefix) {
		this.adminPrefix = adminPrefix;
	}

	public String getAdminIndex() {
		return adminIndex;
	}

	public void setAdminIndex(String adminIndex) {
		this.adminIndex = adminIndex;
	}
	
	public String getAdminLogin() {
		return adminLogin;
	}

	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}

}
