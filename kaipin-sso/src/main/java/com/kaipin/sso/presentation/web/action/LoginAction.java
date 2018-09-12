package com.kaipin.sso.presentation.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.web.WebKissoConfigurer;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.kaipin.sso.WebStatus;
import com.kaipin.sso.common.message.Messages;
import com.kaipin.sso.entity.web.user.UserCategory;
import com.kaipin.sso.entity.web.user.UserLocalAuth;
import com.kaipin.sso.exception.ValidateException;
import com.kaipin.sso.model.LoginPacket;
import com.kaipin.sso.model.OutPacket;
import com.kaipin.sso.nosql.redis.RedisFactory;
import com.kaipin.sso.nosql.redis.RedisHelper;
import com.kaipin.sso.presentation.BaseAction;
import com.kaipin.sso.repository.dao.web.user.IUserLocalAuthDao;
import com.kaipin.sso.service.web.company.ICompanyBaseUserService;
import com.kaipin.sso.service.web.user.IUserLocalAuthService;
import com.kaipin.sso.util.JsonUtils;
import com.kaipin.sso.web.token.WebToken;


/***
 * 用户授权管理
 * @author Tony
 *
 */
@Controller
@RequestMapping("/web")
public class LoginAction extends BaseAction {

	@Autowired
	private IUserLocalAuthService userLocalAuthService;
	

	/**
	 * 用户登陆
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 * @throws Exception
	 *             ResponseEntity<Object>
	 */
	@RequestMapping(value = "/auth/login", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<Object> login(
			@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "password", defaultValue = "") String password, HttpServletRequest request,
			HttpServletResponse response) throws ValidateException, Exception {
		 response.setHeader("Access-Control-Allow-Credentials", "true");
		    
		if (StringUtils.isEmpty(username)) {

			throw new ValidateException("" + WebStatus.WEB_R_ERROR, Messages.getString("error.usernameRequired"));

		}

		if (StringUtils.isEmpty(password)) {

			throw new ValidateException("" + WebStatus.WEB_R_ERROR, Messages.getString("error.pwdRequired"));

		}
		
		Object json =  userLocalAuthService.login(request, response, username, password);
		
		//response.reset();
		// response.sendRedirect(json.redirect_uri);

		return new ResponseEntity<Object>(json, HttpStatus.OK);
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 * @throws Exception
	 */

	@RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
	public ModelAndView logout(@RequestParam(value = "redirect_uri", defaultValue = "") String redirect_uri,
			HttpServletRequest request, HttpServletResponse response) throws ValidateException, Exception {

		System.out.println("redirect_uri->" + redirect_uri);

		userLocalAuthService.logout(request, response);

		String ruri = redirect_uri;

		if (ruri == null || ruri.equals("")) {
			ruri = SSOHelper.getKissoService().getConfig().getLoginUrl();

		}

		return new ModelAndView(redirectTo(ruri));
	}

	/**
	 * 用户验证
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/rCookie", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<Object> rCookie(
			@RequestParam(value = "uid", defaultValue = "") String uid, 
			@RequestParam(value = "uType", defaultValue = "") String uType, 
			HttpServletRequest request,
			HttpServletResponse response) throws ValidateException, Exception {
		 response.setHeader("Access-Control-Allow-Credentials", "true");
		    
		if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(uType)) {

			throw new ValidateException("" + WebStatus.WEB_R_ERROR_PARAM, 
					Messages.getString("error.paramIsErro"));

		}

		UserLocalAuth user = userLocalAuthService.selectByPramiKey(uid);
		WebToken token = (WebToken) userLocalAuthService.rCookie(request, response, user);
		Object json =  token;

		return new ResponseEntity<Object>(json, HttpStatus.OK);
	}

	
}
