package com.common.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {
	Logger log = Logger.getLogger(RootController.class.getName());
	
	@RequestMapping("")
	 public String indexInit(HttpServletRequest request, HttpSession session){
		Cookie[] cookies = request.getCookies();
		String userName = "";
		String passWord = "";
		if (cookies != null) {  
			//获取cookie中的用户名信息
		    for (Cookie c : cookies) {  
		        if ("kpUserName".equals(c.getName())) {  
		        	try {
						userName=URLDecoder.decode(c.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}  
		        }
		        if("kpPassWord".equals(c.getName())) {
		        	//选中记录账号
		        	try {
		        		passWord = URLDecoder.decode(c.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
		        }
		    }  
		}
		System.out.println(userName);
		System.out.println(passWord);
		return "/index";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpSession session){
		return "/index";
	}
}
