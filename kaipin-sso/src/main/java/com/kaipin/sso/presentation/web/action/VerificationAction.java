package com.kaipin.sso.presentation.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.sso.exception.ValidateException;
import com.kaipin.sso.presentation.BaseAction;
import com.kaipin.sso.service.web.user.IUserLocalAuthService;

/**
 * 登陆检查
 * 
 * @author Tony
 *
 */
@Controller
@RequestMapping("/web")
public class VerificationAction extends BaseAction {

	@Autowired
	private IUserLocalAuthService userLocalAuthService;

	/**
	 * 用户登陆检查
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 * @throws Exception
	 */
	@RequestMapping(value = "/login_check", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<Object> loginCheck(HttpServletRequest request, HttpServletResponse response)
			throws ValidateException, Exception {

		Object result = userLocalAuthService.loginCheck(request, response);

		return new ResponseEntity<Object>(result, HttpStatus.OK);

	}

}
