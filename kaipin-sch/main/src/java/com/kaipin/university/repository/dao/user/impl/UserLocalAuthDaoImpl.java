package com.kaipin.university.repository.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.repository.dao.user.IUserLocalAuthDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class UserLocalAuthDaoImpl extends MybatisBaseDAO<UserLocalauth,String> implements IUserLocalAuthDao{
	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.user.IUserLocalAuthDao";
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
		try {
			return (UserLocalauth) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public int updateByPrimaryKeySelective(UserLocalauth record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@Override
	public UserLocalauth selectByOrgId(String orgId) {
		return (UserLocalauth) this.selectOne("selectByOrgId", orgId);
	}


	

}
