package com.kaipin.enterprise.repository.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.repository.dao.user.ICompanyInfoDao;

@Repository
public class CompanyInfoDaoImpl extends MybatisBaseDAO<CompanyInfo,String> implements ICompanyInfoDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.user.ICompanyInfoDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(CompanyInfo record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public CompanyInfo selectByPrimaryKey(String id) {
		return (CompanyInfo) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyInfo record) {
		return update("updateByPrimaryKeySelective", record);
	}

}
