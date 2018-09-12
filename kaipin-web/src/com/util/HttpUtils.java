

package com.util;

import javax.servlet.http.HttpServletRequest;





public class HttpUtils {

	/**
	 * 得到请求的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		StringUtil.isEmpty("");
		if (StringUtil.isEmpty(ip)) {
			ip = request.getHeader("Host");
		}
		if (StringUtil.isEmpty(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtil.isEmpty(ip)) {
			ip = "0.0.0.0";
		}
		return ip;
	}

	/**
	 * 得到请求的根目录
	 * 
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		return basePath;
	}
	
	
 
	
	
	

	/**
	 * 得到结构目录
	 * 
	 * @param request
	 * @return
	 */
	public static String getContextPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return path;
	}
	
	
	public static String getURI(HttpServletRequest request) {
	return URLHelper.getURI(request);
	}
	
	
	
	
	
	

	/**
	 * @return
	 */
	public static String getRealPath() {
		return "";
	}
}
