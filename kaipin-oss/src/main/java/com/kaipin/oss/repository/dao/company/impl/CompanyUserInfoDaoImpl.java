package com.kaipin.oss.repository.dao.company.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.repository.dao.company.CompanyUserInfoDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class CompanyUserInfoDaoImpl extends MybatisBaseDAO<CompanyUserInfo, String>implements CompanyUserInfoDao{

	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.mapper.company.CompanyUserInfo";
	
	@Override
	public String getDefaultSqlNamespace() {
		return NAMESPACE_INFO;
	}

	@Override
	public CompanyUserInfo selectByPrimaryKey(String id) {
		return (CompanyUserInfo) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public boolean updateByPrimaryKeySelective(CompanyUserInfo function) {
		try {
			this.update("updateByPrimaryKeySelective", function);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
