package com.kaipin.oss.repository.dao.common.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.common.UserLocalauth;
import com.kaipin.oss.repository.dao.common.IUserLocalAuthDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class UserLocalAuthDaoImpl extends MybatisBaseDAO<UserLocalauth, String> implements IUserLocalAuthDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.oss.repository.dao.common.IUserLocalAuthDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(UserLocalauth record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public UserLocalauth selectByPrimaryKey(String id) {
		return (UserLocalauth) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public UserLocalauth selectByOrgId(String orgId) {
		return (UserLocalauth) this.selectOne("selectByOrgId", orgId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLocalauth record) {
		return this.update("updateByPrimaryKeySelective", record);
	}

}
