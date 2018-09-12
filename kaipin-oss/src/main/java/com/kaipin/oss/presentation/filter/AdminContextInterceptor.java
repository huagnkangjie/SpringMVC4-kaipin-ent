package com.kaipin.oss.presentation.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

/**
 * 上下文信息拦截器
 *  
 *
 */
public class AdminContextInterceptor  extends HandlerInterceptorAdapter {
	
	
	protected static final String PART_DIVIDER_TOKEN = ":";
	protected static final String PERMISSION_DIVIDER_TOKEN = ",";
	protected static final String HOLDER_DIVIDER_TOKEN = "#";
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//DynamicSpecifications.putRequest(request);
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		String uri=getURI(request);
		
		if (exclude(uri)) {
		return true;
	}
		
		final HandlerMethod handlerMethod = (HandlerMethod)handler;

		Method method = handlerMethod.getMethod();

		boolean firstPermitted = false;
	 
				// 进行初次验证，确保shiro中用户的权限被初始化。 
				if (!firstPermitted) {
					Subject subject = SecurityUtils.getSubject();
					if (!subject.isPermitted("admin:1")){ 
					 throw new UnauthorizedException("数据权限验证失败！");
					}
					firstPermitted = true;
				}
			

		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		
		
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}
	
	
	private boolean exclude(String uri) {
		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static String getURI(HttpServletRequest request)
			throws IllegalStateException {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);
		int start = 0, i = 0, count = 0;
		if (!StringUtils.isBlank(ctxPath)) {
			count++;
		}
		while (i < count && start != -1) {
			start = uri.indexOf('/', start + 1);
			i++;
		}
		
		if (start <= 0) {
			throw new IllegalStateException(
					"admin access path not like '' pattern: "
							+ uri);
		}
		return uri.substring(start);
	}
	
	
	
	private boolean auth = true;
	private String[] excludeUrls;
	
	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
}
