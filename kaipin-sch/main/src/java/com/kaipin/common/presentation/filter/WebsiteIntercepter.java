package com.kaipin.common.presentation.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.DesBean;
import com.kaipin.common.entity.EntUser;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.DESEncryptUtil;
import com.kaipin.common.util.HttpUtils;
import com.kaipin.common.util.JsonUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.RequestUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.user.ICompanyInfoService;

/**
 * web基础公共配置
 * 
 *
 */
public class WebsiteIntercepter extends HandlerInterceptorAdapter {
	
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setContentType("text/html; charset=utf-8");
		String requestPath = RequestUtil.getRequestPath(request);// 用户访问的资源地址
		String requestHeader = request.getHeader("X-Requested-With");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	  // response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	   response.setHeader("Access-Control-Allow-Credentials", "true");
		
		String uri = request.getContextPath();
		Cookie cookie = CookieUtil.getCookieByName(request, Constant.USER_ORG_ID);
		String decrypt = CookieUtil.getCookieInfoByKey(request, Constant.USER_TOKEN);
		String des =  new DESEncryptUtil(Constant.TOKEN_ACCESS_KEY).decryptDES(decrypt);
		
		Cookie c[] = request.getCookies();
		DesBean desBean = null;
		
		//直接过滤的请求
		if(requestPath.startsWith("/resume/share/")){
			return true;
		}
		if(requestPath.startsWith("/basic/detail")){
			return true;
		}
		
		if(StringUtil.isNotEmpty(des)){
			String ss = des.split("#")[0];
			desBean = JsonUtil.jsonToObj(ss, DesBean.class);
			System.out.println("解密数据   ：   t_type==>"+desBean.getType() + "  uid==>" + desBean.getUid() + "  org_id==>" + desBean.getGroupId());
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
				SchoolInfoLink link = schLinkService.selectByPrimaryKey(user.getOrganizationId());
				if(link != null){
					sessionInfo = new EntUser();
					sessionInfo.setId(Constant.VALUE_ZERO);
				}
				
			}
			
		}
		//登录
		if(sessionInfo == null && !requestPath.equals("/loading")) {
			if(requestPath.equals("/annexController/uploadImg?oper=regedit") ||
					requestPath.equals("/annexProcess/process")){
				return true;
			}
			if ("XMLHttpRequest".equalsIgnoreCase(requestHeader)) {// AJAX REQUEST PROCESS
				if(requestPath.equals("/home/getFollowAndFans") || 
						requestPath.equals("/comm/followfans/isFollowFans")){
					return true;
				}
				response.getWriter().print("timeout");
				return false;
            }
			response.sendRedirect(uri+"/loading");
			return false;
		}
		return true;

		//return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		PropUtil pro = new PropUtil();
		String staticPath = pro.getValue("static.url");

		/*
		 * 系统配置参数 短路经 _变量_ ；
		 */
		String basePath = HttpUtils.getBasePath(request);
		String contextPath = HttpUtils.getContextPath(request);
		String uri = HttpUtils.getURI(request);

		request.setAttribute("BASE_PATH", basePath); // 访问地址

		request.setAttribute("CONTEXT_PATH", contextPath); // 上下文路径

		request.setAttribute("URI_PATH", basePath + uri); // uri全路径
		request.setAttribute("_URI_", uri); // 访问的uri

		request.setAttribute("PUBLIC_PATH", contextPath + "/static"); // 全路径css,js,img资源

		request.setAttribute("_PUBLIC_", "/static"); // css,js,img资源
		
		request.setAttribute("STATIC_ENT", staticPath + "/company");//企业静态资源目录
		request.setAttribute("STATIC_SCH", staticPath + "/university");//高校静态资源目录
		request.setAttribute("STATIC_STU", staticPath + "/student");//学生静态资源目录
		request.setAttribute("STATIC_COM", staticPath + "/common");//公共静态资源目录
	}

}
