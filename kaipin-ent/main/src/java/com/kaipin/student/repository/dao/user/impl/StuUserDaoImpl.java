package com.kaipin.student.repository.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.student.model.user.StuUser;
import com.kaipin.student.repository.dao.user.IStuUserDao;

@Repository
public class StuUserDaoImpl extends MybatisBaseDAO<StuUser, String> implements IStuUserDao{

	
	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.student.repository.dao.user.IStuUserDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(StuUser record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public StuUser selectByPrimaryKey(String id) {
		return (StuUser) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(StuUser record) {
		return update("updateByPrimaryKeySelective", record);
	}

}
