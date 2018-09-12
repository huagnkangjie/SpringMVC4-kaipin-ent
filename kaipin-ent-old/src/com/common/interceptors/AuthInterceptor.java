package com.common.interceptors;

import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.common.Constant;
import com.enterprise.model.common.EntUser;
import com.enterprise.model.user.CompanyInfo;
import com.enterprise.model.user.UserLocalauth;
import com.enterprise.pojo.DesBean;
import com.enterprise.service.common.ISSOEncrypt;
import com.enterprise.service.user.ICompanyInfoService;
import com.enterprise.service.user.IUserLocalauthService;
import com.util.CookieUtil;
import com.util.DESEncryptUtil;
import com.util.JsonUtil;
import com.util.PropUtil;
import com.util.RequestUtil;
import com.util.StringUtil;



/**
 * 权限拦截器
 * 
 * @author Mr_H
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger
			.getLogger(AuthInterceptor.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ICompanyInfoService companyInfoService;

	public static Logger getLogger() {
		return logger;
	}


	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
		String url = request.getHeader("REFERER");//浏览器完整URL
//		if (null == modelAndView) {
//			return;
//		}
		//配置系统的参数
		//PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
		
	
	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String requestPath = RequestUtil.getRequestPath(request);// 用户访问的资源地址
		String requestHeader = request.getHeader("X-Requested-With");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	  // response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	   response.setHeader("Access-Control-Allow-Credentials", "true");
	   
	   //职位分享
 		if(requestPath.startsWith("/position/id") || requestPath.startsWith("/resume/counts.do")){
 			return true;
 		}
		
		String uri = request.getContextPath();
		Cookie cookie = CookieUtil.getCookieByName(request, Constant.USER_ORG_ID);
		String decrypt = CookieUtil.getCookieInfoByKey(request, Constant.USER_TOKEN);
		String des =  new DESEncryptUtil(Constant.TOKEN_ACCESS_KEY).decryptDES(decrypt);
		
		Cookie c[] = request.getCookies();
		System.out.println(c);
		DesBean desBean = null;
		if(StringUtil.isNotEmpty(des)){
			String ss = des.split("#")[0];
			desBean = JsonUtil.jsonToObj(ss, DesBean.class);
			System.out.println("解密数据   ：  "+desBean);
		}
		EntUser sessionInfo = null;
		if(cookie == null){
			sessionInfo = null;
		}else{
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			UserLocalauth user = localUserService.selectByPrimaryKey(cookie_uid);
			if(user == null){
				sessionInfo = null;
			}else{
				System.out.println(user.getOrganizationId());
				CompanyInfo info = companyInfoService.selectByPrimaryKey(user.getOrganizationId());
				if(info != null){
					sessionInfo = new EntUser();
					sessionInfo.setId(Constant.VALUE_ZERO);
				}
				
			}
		}
		
		//验证用户正确性
		String loginUid = "";
		if(requestPath.startsWith("/basicConctroller/init?userId=")){
			String uidArray[] = requestPath.split("=");
			if(uidArray.length > 1){
				loginUid = uidArray[1];
				if( desBean != null && !loginUid.equals(desBean.getUid())){
					sessionInfo = null;
				}
			}
		}
		
		//注册
		if(requestPath.startsWith("/regedit")){
			return true;
		}
		//扩展接口
		if(requestPath.startsWith("/position/id")){
			return true;
		}
		//扩展接口
		if(requestPath.startsWith("/ext")){
			return true;
		}
		//注册时附件上传
		if(requestPath.startsWith("/annexController/uploadImg.do?oper=regedit")){
			return true;
		}
		//注册时附件上传
		if(requestPath.startsWith("/annexProcess/process.do")){
			return true;
		}
		
		//首页登录
		if(requestPath.equals("/loginController/indexLogin")){
			return true;
		}
		
		//手机端消息推送到企业端
		if(requestPath.startsWith("/msg/sendMsgs")){
			return true;
		}
		
		//关于我们
		if(requestPath.equals("/loginController/about")){
			return true;
		}
		//帮助中心
		if(requestPath.equals("/loginController/help")){
			return true;
		}
		
		//登录
		if(sessionInfo == null && !requestPath.equals("/loginController/loading")) {
			if(requestPath.equals("/annexController/uploadImg?oper=regedit") ||
					requestPath.equals("/annexProcess/process")){
				return true;
			}
			if ("XMLHttpRequest".equalsIgnoreCase(requestHeader)) {// AJAX REQUEST PROCESS
				response.getWriter().print("timeout");
				return false;
            }
			response.sendRedirect(uri+"/loginController/loading");
			return false;
		}
		return true;
	}
	
}
