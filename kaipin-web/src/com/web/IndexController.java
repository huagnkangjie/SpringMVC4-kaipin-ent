package com.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.util.CookieUtil;
import com.util.HttpRequestUtil;


/**
 * kaipin-sso
 * 系统首页
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	Logger log = Logger.getLogger(IndexController.class.getName());
	
	public static String INDEX = "index";
	public static String LOGIN = "login";
	public static String CENTER_INDEX = "";
	

	/**
	 * 系统首页.
	 * 如果当前用户已经登录，则直接登录系统
	 * 如果当前用户cookie已经过期，则需要登录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(HttpServletRequest request, Model model){
		try {
			
			return "index";
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	/**
	 * 系统首页.
	 * 如果当前用户已经登录，则直接登录系统
	 * 如果当前用户cookie已经过期，则需要登录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	public String initPage(HttpServletRequest request, Model model){
		try {
			
			return "index";
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
	/**
	 * 个人中心页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center")
	public String center(HttpServletRequest request, Model model, HttpServletResponse response){
		try {
			String ss = HttpRequestUtil.sendPost("http://192.168.1.56:8080/kaipin-sso/web/auth/login", "username=1059976050@qq.com2&password=123123");
	    	System.out.println(ss);
	    	response.sendRedirect("http://192.168.1.56:8080/kaipin-ent/basicConctroller/init");
			return "center";
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
	/**
	 * 登录页面
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model, HttpServletResponse response){
		try {
//			boolean  flag = CookieUtil.addCookie(response, Constants.USER_COOKIE, "用户id:开始时间:过期时间:用户类型");
//			System.out.println(flag);
			return "login";
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
	/**
	 * 注册首页
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/regedit")
	public String register(HttpServletRequest request, Model model, HttpServletResponse response){
		try {
			return "/regedit/regedit";
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
	
	/**
	 * 退出
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/layout")
	public String layout(HttpServletRequest request, HttpServletResponse response, Model model){
		try {
			boolean flag = CookieUtil.delCookie(request, response);
			System.out.println(flag);
			return "index";
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
	/**
	 * 帮助中心
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/v/help")
	public String help(HttpServletRequest request, HttpServletResponse response, Model model){
		try {
			if(CookieUtil.isNotInvalid(request)){
				return "/inc/help";
			}else{
				return "/inc/help_login";
			}
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
	/**
	 * 关于我们
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/v/about")
	public String about(HttpServletRequest request, HttpServletResponse response, Model model){
		try {
			if(CookieUtil.isNotInvalid(request)){
				return "/inc/about";
			}else{
				return "/inc/about_index";
			}
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
}
