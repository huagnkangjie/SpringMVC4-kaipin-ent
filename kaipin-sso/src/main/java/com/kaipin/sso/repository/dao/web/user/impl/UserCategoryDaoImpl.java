package com.kaipin.sso.repository.dao.web.user.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.sso.entity.web.user.UserCategory;
import com.kaipin.sso.repository.dao.web.user.IUserCategoryDao;
import com.kaipin.sso.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class UserCategoryDaoImpl   extends MybatisBaseDAO< UserCategory,String> implements  IUserCategoryDao{

	@Override
	public UserCategory getById(String categoryId) {
	 
		return	super.getById(categoryId);
		
	 
	}

	@Override
	public String getDefaultSqlNamespace() {
	 
		return "web.UserCategoryMapper";
	}

}
