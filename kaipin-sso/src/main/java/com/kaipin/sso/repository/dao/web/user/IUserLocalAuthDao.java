package com.kaipin.sso.repository.dao.web.user;

import java.util.Map;

import com.kaipin.sso.entity.web.user.UserLocalAuth;

public interface IUserLocalAuthDao {

 
	/**
	 * 登陆
	 * @param username 用户名
	 * @return
	 */
	public UserLocalAuth getLoginUser(Map<String,Object> map);
	
	/**
	 * 根据用户id获取用户信息
	 * @param id
	 * @return
	 */
	public UserLocalAuth selectByPramiKey(String id);
	
	/**
	 * 更新最后登录时间
	 * @param user
	 * @return
	 */
	public boolean updateUserLoginTime(Map<String,Object> map);
	
}
