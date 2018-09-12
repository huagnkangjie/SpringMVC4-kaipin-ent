package com.common.filters;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.common.constants.Constant;
import com.model.user.User;
import com.util.CookieUtil;
import com.util.HttpUtils;
import com.util.RequestUtil;



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
		// 系统配置参数
		String basePath = HttpUtils.getBasePath(request);
		
		String contextPath = HttpUtils.getContextPath(request);
		
		String uri=HttpUtils.getURI(request);
		if (null == modelAndView) {
			return;
		}
		modelAndView.addObject("BASE_PATH", basePath); //访问地址
		
		modelAndView.addObject("CONTEXT_PATH", contextPath); //上下文路径
		
		modelAndView.addObject("UPLOAD_BASE_PATH", basePath + "/upload");
 
		modelAndView.addObject("PUBLIC_PATH", contextPath+"/public");  //css,js,img资源
		
		modelAndView.addObject("_PUBLIC_",  "/public");  //css,js,img资源
 
		modelAndView.addObject("URI_PATH", basePath+uri);  // uri全路径
		
		modelAndView.addObject("_URI_",  uri);  // 访问的uri
		
		
		modelAndView.addObject("TITLE",  Constant.TITLE);  
		modelAndView.addObject("KEYWORDS",  Constant.KEYWORDS);  
		modelAndView.addObject("CONTENT",  Constant.CONTENT); 
		
	
	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String requestPath = RequestUtil.getRequestPath(request);// 用户访问的资源地址
		String requestHeader = request.getHeader("X-Requested-With");
		String uri = request.getContextPath();
		
		//指导性阅读
		/*if(requestPath.startsWith("/v/")){
			return true;
		}
		
		//注册
		if(requestPath.startsWith("/regedit/")){
			return true;
		}
		
		//码表
		if(requestPath.startsWith("/commCode")){
			return true;
		}
		
		//附件
		if(requestPath.startsWith("/annex")){
			return true;
		}
		//注册推荐关注
		if(requestPath.startsWith("/rfollow")){
			return true;
		}

		if(!CookieUtil.isNotInvalid(request)
				&& !requestPath.equals("/init")
				&& !requestPath.equals("/login")
				&& !requestPath.equals("/regedit")){
			if ("XMLHttpRequest".equalsIgnoreCase(requestHeader)) {// AJAX REQUEST PROCESS
				response.getWriter().print("timeout");
				return false;
            }
			response.sendRedirect(uri+"/init");
			return false;
		}else if(CookieUtil.isNotInvalid(request)
				&& requestPath.equals("/init")){
			response.sendRedirect(uri+"/center");
			return true;
		}*/
		
		return true;
	}
}
