package com.kaipin.sso.repository.dao.web.user.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.sso.entity.web.user.UserLocalAuth;
import com.kaipin.sso.repository.dao.web.user.IUserLocalAuthDao;
import com.kaipin.sso.repository.mybatis.dao.MybatisBaseDAO;


@Repository
public class UserLocalAuthDaoImpl   extends MybatisBaseDAO<UserLocalAuth,String> implements  IUserLocalAuthDao {
	//private static final String NAMESPACE_INFO = "web.UserLocalAuthMapper";

	@Override
	public UserLocalAuth getLoginUser(Map<String,Object> map) {
	 
	return  (UserLocalAuth)	this.selectOne("getLoginUser", map);
 
	}

	
	
	@Override
	public String getDefaultSqlNamespace() {
	 
		return "web.UserLocalAuthMapper";
		
	}



	@Override
	public UserLocalAuth selectByPramiKey(String id) {
		return  (UserLocalAuth)	this.selectOne("selectByPramiKey", id);
	}



	@Override
	public boolean updateUserLoginTime(Map<String,Object> map) {
		try {
			this.update("updateUserLoginTime", map);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
