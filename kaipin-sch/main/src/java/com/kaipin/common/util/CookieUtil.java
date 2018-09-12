package com.kaipin.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaipin.common.constants.Constant;



/**
 * cookie操作类
 * @author Mr-H
 *
 */
public class CookieUtil {
	
	/**
	 * 获取企业id
	 * @param request
	 * @return
	 */
	public static String getCookieInfoByKey(HttpServletRequest request, String key){
		Cookie cookie = CookieUtil.getCookieByName(request, key);
		String value = "";
		if(cookie != null){
			value = cookie.getValue();
		}
		return value;
	}
	
	public static String getUserInfo(HttpServletRequest request,int index){
		String dest = each(getCookieAarry(request));
		String destArray[] = null;
		if(StringUtil.isNotEmpty(dest)){
			destArray = dest.split(":");
			if(index < destArray.length && index >= 0){
				dest =  dest.split(":")[index];
			}
		}
		return dest;
	}
	
	/**
	 * 添加cookie
	 * @param response
	 * @param name
	 * @param value
	 * @return
	 */
	public static boolean addCookie(HttpServletResponse response, String name ,String value){
		try {
			Cookie cookie = new Cookie(name, URLEncoder.encode(  
					value, "utf-8")); 
            cookie.setMaxAge(60 * 60 * 24);// 设置为30min
            cookie.setPath("/");
            response.addCookie(cookie);
            return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除cookie
	 * @param request
	 * @param response
	 * @return
	 */
	public static boolean delCookie(HttpServletRequest request,HttpServletResponse response){
		try {
			Cookie cookie = getCookieByName(request, Constant.USER_TOKEN);
			cookie.setValue(null);
	        cookie.setMaxAge(0);// 立即销毁cookie
	        cookie.setPath("/");
	        response.addCookie(cookie);
	        return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 获取cookie数组
	 * @param request
	 * @return
	 */
	public static Cookie[] getCookieAarry(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
	       return cookies;
	    }else{
	    	return null;
	    }
	}

	/**
	 * 判断该cookie是否失效   
	 * @return
	 */
	public static boolean isNotInvalid(HttpServletRequest request){
		boolean flag = false;
		try {
			Cookie[] cookies = getCookieAarry(request);
			if (cookies != null) {  
				//获取cookie中的用户名信息
			    for (Cookie c : cookies) {  
			        if (Constant.USER_TOKEN.equals(c.getName())) {  
			        	flag = true;
			        }
			    }  
			}
			return flag;
		} catch (Exception e) {
			return flag;
		}
	}
	
	/**
	 * 获取用户cookie字符串
	 * @param cookies
	 * @return
	 */
	public static String getUserCookie(HttpServletRequest request){
		return each(getCookieAarry(request));
	}
	
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
	/**
	 * 遍历cookie
	 * @param cookies
	 * @return
	 */
	public static String each(Cookie[] cookies){
		String cookie = "";
		try {
			if (cookies != null) {  
				//获取cookie中的用户名信息
			    for (Cookie c : cookies) {  
			        if (Constant.USER_TOKEN.equals(c.getName())) {  
			        	cookie = URLDecoder.decode(c.getValue(), "utf-8");
			        }
			    }  
			}
			return cookie;
		} catch (Exception e) {
			return cookie;
		}
		
	}
}
