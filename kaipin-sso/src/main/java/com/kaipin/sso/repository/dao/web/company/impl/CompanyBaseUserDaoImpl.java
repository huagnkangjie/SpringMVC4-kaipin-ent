package com.kaipin.sso.repository.dao.web.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.sso.entity.web.company.CompanyBaseUser;
import com.kaipin.sso.entity.web.user.UserLocalAuth;
import com.kaipin.sso.repository.dao.web.company.ICompanyBaseUserDao;
import com.kaipin.sso.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class CompanyBaseUserDaoImpl extends MybatisBaseDAO<CompanyBaseUser,String> implements ICompanyBaseUserDao {

	@Override
	public CompanyBaseUser getCompanyUserById(String id) {
 
		return  (CompanyBaseUser) selectOne("getCompanyUserById",id);
	}

	@Override
	public String getDefaultSqlNamespace() {
	 
		return "web.CompanyBaseUserMapper";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectDoc(String id) {
		return selectList("selectDoc", id);
	}

}
