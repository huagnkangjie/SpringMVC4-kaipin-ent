package com.kaipin.enterprise.repository.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.user.CompanyUserInfo;
import com.kaipin.enterprise.repository.dao.user.ICompanyUserInfoDao;

@Repository
public class CompanyUserInfoDaoImpl extends MybatisBaseDAO<CompanyUserInfo, String> implements ICompanyUserInfoDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.user.ICompanyUserInfoDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insert(CompanyUserInfo record) {
		try {
			this.insert("insert", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(CompanyUserInfo record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public CompanyUserInfo selectByPrimaryKey(String id) {
		return (CompanyUserInfo) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyUserInfo record) {
		return this.update("updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(CompanyUserInfo record) {
		return this.update("updateByPrimaryKey", record);
	}

}
