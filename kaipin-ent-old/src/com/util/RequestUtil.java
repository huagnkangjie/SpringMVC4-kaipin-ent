package com.util;

import javax.servlet.http.HttpServletRequest;

/**
 * request工具类
 * 
 * @author  
 * 
 */
public class RequestUtil {

	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = null;
		if(!StringUtil.isEmpty(request.getQueryString())) {
			requestPath = request.getRequestURI() + "?" + request.getQueryString();
		} else {
			requestPath = request.getRequestURI();
		}
		
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}

}
