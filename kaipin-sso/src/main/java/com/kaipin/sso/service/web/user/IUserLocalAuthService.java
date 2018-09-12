package com.kaipin.sso.service.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaipin.sso.entity.web.user.UserLocalAuth;
import com.kaipin.sso.exception.ValidateException;

public interface IUserLocalAuthService {

	/**
	 * 用户登陆
	 * @param request
	 * @param username
	 * @param password
	 * @return 返回结果
	 */
	public Object login(HttpServletRequest request,HttpServletResponse response,String username,String password) throws ValidateException,Exception;
	
	/**
	 * 登出 
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 * @throws Exception
	 */
	public Object logout(HttpServletRequest request,HttpServletResponse response) throws ValidateException,Exception;
	
	/**
	 * 检查用户登陆信息
	 * @param request
	 * @param response
	 * @return 用户登陆信息,WebToken
	 * @throws ValidateException
	 * @throws Exception
	 */
	public Object loginCheck(HttpServletRequest request,HttpServletResponse response) throws ValidateException,Exception;
	
	/**
	 * 根据用户id获取用户信息
	 * @param id
	 * @return
	 */
	public UserLocalAuth selectByPramiKey(String id);
	
	/**
	 * 重写cookie
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	public Object rCookie(HttpServletRequest request,HttpServletResponse response,UserLocalAuth user);
	
	/**
	 * 更新最后登录时间
	 * @param user
	 * @return
	 */
	public boolean updateUserLoginTime(Map<String,Object> map);
	
	
}
