package com.common.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.Constant;
import com.common.pojo.Json;
import com.enterprise.model.EntBaseinfo;
import com.enterprise.model.common.EntUser;
import com.enterprise.model.user.CompanyInfo;
import com.enterprise.service.login.IEntBaseinfoService;
import com.enterprise.service.login.IEntUserService;
import com.enterprise.service.user.ICompanyInfoService;
import com.enterprise.service.user.IEntBaseUserService;
import com.util.MD5Util;
import com.util.StringUtil;
import com.util.UuidUtil;
/**
 * 用户登录、注销操作类
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {
	
	private static Log log = LogFactory.getLog(LoginController.class);	
	
	@Autowired
	private IEntBaseinfoService entBaseinfoService;//企业基本信息
	@Autowired
	private IEntUserService userService;//企业用户信息
	@Autowired
	private IEntBaseUserService entBaseUserService;//企业用户多表联查
	@Autowired
	private ICompanyInfoService companyInfoService;
	
	@RequestMapping({"/init"})
	public String index(HttpServletRequest request){
		System.out.println(".......");
		return "/index";
	}
	
	/**
	 * 返回登页面
	 * @return
	 */
	@RequestMapping({"/index"})
	public String index(HttpServletRequest request, Model model){
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
		model.addAttribute("userName", userName);
		model.addAttribute("passWord", passWord);
		String status_oper = Constant.VALUE_ZERO;//默认0
		EntUser user = (EntUser) request.getSession().getAttribute(Constant.USER);
		if(user != null){
			status_oper = Constant.VALUE_ONE;
		}
		model.addAttribute("status_oper", status_oper);
		return "/login";
	}
	@RequestMapping({"/indexLogin"})
	public String indexLogin(){
		return "/login";
	}
	
	/**
	 * logo
	 * @return
	 */
	@RequestMapping({"/header"})
	public String header(){
		return "/main/header";
	}
	/**
	 * 登录成功后进入主页面
	 * @return
	 */
	@RequestMapping({"/main"})
	public String main(HttpServletRequest request, Model model){
		try {
			EntUser user = (EntUser)request.getSession().getAttribute(Constant.USER);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(user.getCompanyId());
			model.addAttribute("bg",info.getBgUrl());
			model.addAttribute("logo",info.getLogo());
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
		}
		return "/ent/basic/basic";
	}
	
	
	/**
	 * 登录
	 * @param session
	 * @param response
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping({"/login"})
	@ResponseBody
	public Json login(HttpSession session,HttpServletResponse response, HttpServletRequest request,
			String userName, String password, String code) throws UnsupportedEncodingException{
		try {
			Json json = new Json();
			request.setCharacterEncoding("utf-8"); 
			response.setCharacterEncoding("UTF-8");  
	        response.setContentType("text/html");
	        
	        Cookie orgId = new Cookie("org_id", URLEncoder.encode(  
					"3cbc9b7f-abef-488c-8896-16f108deebe0", "utf-8")); 
			orgId.setMaxAge(60*60*24*7);//一个星期
			orgId.setPath("/");
			response.addCookie(orgId);
			json.setSuccess(true);
			
	        
//	        String valid = session.getAttribute("VALIDATION_CODE").toString().toLowerCase();
//	        System.out.println(valid);
			
			//if(valid.equals(code.toLowerCase())) {
				HashMap<String,Object> map=new HashMap<String, Object>();
				map.put("userName", userName.toLowerCase());
				map.put("password", MD5Util.encrypt(password));
				List<Map<String,Object>> list = entBaseUserService.login(map);
				EntUser user = new EntUser();
				if(list.size() > 0){
					user = fillEntUserClass(list);
					if(user != null){
						if(String.valueOf(user.getIsCheckMail()).equals(Constant.VALUE_ONE) &&
								String.valueOf(user.getEnable()).equals(Constant.VALUE_ONE)){
							//EntBaseinfo info = entBaseinfoService.selectByPrimaryKey(user.getCompanyId());
							session.setAttribute(Constant.LOGO, user.getLogo());
							session.setAttribute(Constant.USER, user);
							//记住用户名和密码
							Cookie cookieName = new Cookie("kpUserName", URLEncoder.encode(  
					       		userName, "utf-8")); 
//							Cookie cookiePw = new Cookie("kpPassWord", URLEncoder.encode(  
//				       	 		password, "utf-8"));
							cookieName.setMaxAge(60*60*24*7);//一个星期
							cookieName.setPath("/");
//							cookiePw.setMaxAge(60*60*24*7);
//							cookiePw.setPath("/");
							response.addCookie(cookieName);
//							response.addCookie(cookiePw);
							json.setSuccess(true);
						}else if(String.valueOf(user.getIsCheckMail()).equals(Constant.VALUE_ZERO)){
							json.setObj(Constant.VALUE_ZERO);
							json.setMsg("请到邮箱激活账号");
							json.setSuccess(false);
						}else if(String.valueOf(user.getEnable()).equals(Constant.VALUE_ZERO)){
							json.setObj(Constant.VALUE_ONE);
							json.setMsg("账号已禁用，请联系管理员");
							json.setSuccess(false);
						}
					} 
				}else {
					//session.setAttribute(Constant.USER, user);
					json.setMsg("请输入正确的用户名和密码");
					json.setSuccess(false);
				}
			//} 
//		else {
//				j.setSuccess(false);
//				j.setMsg("验证码错误!");
//			}
				return json;
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * 填充用户实体
	 * @param list
	 * @return
	 */
	public EntUser fillEntUserClass(List<Map<String,Object>> list){
		try {
			EntUser user = new EntUser();
			user.setId(String.valueOf(list.get(0).get("id")));
			user.setCompanyId(String.valueOf(list.get(0).get("company_id")));
			user.setPhone(String.valueOf(list.get(0).get("phone")));
			user.setEmail(String.valueOf(list.get(0).get("email")));
			user.setPassword(String.valueOf(list.get(0).get("password")));
			user.setEnable(Byte.valueOf(String.valueOf(list.get(0).get("enable"))));
			String entName = String.valueOf(list.get(0).get("ent_name"));
			if(StringUtil.isNotEmpty(entName)){
				if(entName.length() > 20){
					entName = entName.substring(0, 20) + "...";
				}
			}
			user.setEntName(entName);
			user.setEntSimpleName(String.valueOf(list.get(0).get("ent_simple_name")));
			user.setIsSystem(Byte.valueOf(String.valueOf(list.get(0).get("is_system"))));
			user.setIsCheckMail(Byte.valueOf(String.valueOf(list.get(0).get("is_check_mail"))));
			user.setLogo(String.valueOf(list.get(0).get("logo")));
			user.setVerifyStatus(Byte.valueOf(String.valueOf(list.get(0).get("verify_status"))));
			if(user.getEntName().equals("null")){
				user.setEntName("");
			}
			if(user.getEntSimpleName().equals("null")){
				user.setEntSimpleName("");
			}
			return user;
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 关于我们
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping({"/about"})
	public String about(HttpSession session, HttpServletRequest request, Model model) {
		try {
			EntUser user = (EntUser)request.getSession().getAttribute(Constant.USER);
			if(user != null){
				return "/inc/about";
			}else{
				return "/inc/about_index";
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 加载跳转页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping({"/loading"})
	public String loading(HttpServletRequest request, Model model) {
		try {
			return "/loading";
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping({"/logout"})
	public String logout(HttpSession session, HttpServletRequest request, Model model) {
		if (session != null) {
			session.invalidate();
		}
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
		model.addAttribute("userName", userName);
		model.addAttribute("passWord", passWord);
		return "/login";
	}
	
	/**
	 * 版本太低，不提供支持
	 * @return
	 */
	@RequestMapping({"/downLoad"})
	public String downLoadExplorer() {
		try {
			return "/common/downLoadExplorer";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 帮助中心
	 * @param request
	 * @return
	 */
	@RequestMapping({"/help"})
	public String help(HttpServletRequest request) {
		try {
			String url = "";
			EntUser user = (EntUser)request.getSession().getAttribute(Constant.USER);
			if(user == null){
				url = "/inc/help_login";
			}else{
				url = "/inc/help";
			}
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
