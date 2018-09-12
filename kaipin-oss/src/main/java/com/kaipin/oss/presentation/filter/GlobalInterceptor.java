 

package com.kaipin.oss.presentation.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kaipin.oss.util.HttpUtils;
import com.kaipin.oss.util.PropertyUtils;

 
 
@Component
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	private  PropertyUtils propertyUtils;
	
	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		// 系统配置参数
		String basePath = HttpUtils.getBasePath(request);
		
		String contextPath = HttpUtils.getContextPath(request);
		
		String uri=HttpUtils.getURI(request);
		
		
		modelAndView.addObject("BASE_PATH", basePath); //访问地址
		
		modelAndView.addObject("CONTEXT_PATH", contextPath); //上下文路径
		
		modelAndView.addObject("UPLOAD_BASE_PATH", basePath + "/upload");
 
		modelAndView.addObject("PUBLIC_PATH", contextPath+"/public");  //css,js,img资源
		
		modelAndView.addObject("_PUBLIC_",  "/public");  //css,js,img资源
 
		modelAndView.addObject("URI_PATH", basePath+uri);  // uri全路径
		
		modelAndView.addObject("_URI_",  uri);  // 访问的uri
		
		
		modelAndView.addObject("APP_DEBUG", propertyUtils.getPropertiesString("debug")); 
		

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	 

	}

}
