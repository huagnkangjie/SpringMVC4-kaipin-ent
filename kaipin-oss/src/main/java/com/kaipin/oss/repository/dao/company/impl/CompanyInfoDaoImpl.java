package com.kaipin.oss.repository.dao.company.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.repository.dao.company.CompanyInfoDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class CompanyInfoDaoImpl extends MybatisBaseDAO<CompanyInfo, String>implements CompanyInfoDao{

	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.mapper.company.CompanyInfo";
	
	@Override
	public String getDefaultSqlNamespace() {
		return NAMESPACE_INFO;
	}

	@Override
	public CompanyInfo selectByPrimaryKey(String id) {
		return (CompanyInfo) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public boolean updateByPrimaryKeySelective(CompanyInfo function) {
		try {
			this.update("updateByPrimaryKeySelective", function);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
