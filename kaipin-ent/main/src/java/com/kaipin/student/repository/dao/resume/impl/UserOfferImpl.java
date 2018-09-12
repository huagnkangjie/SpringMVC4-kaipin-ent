package com.kaipin.student.repository.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.student.model.resume.UserOffer;
import com.kaipin.student.repository.dao.resume.IUserOfferDao;

@Repository
public class UserOfferImpl extends MybatisBaseDAO<UserOffer, String> implements IUserOfferDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.student.repository.dao.resume.IUserOfferDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(UserOffer record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public UserOffer selectByPrimaryKey(String id) {
		return (UserOffer) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserOffer record) {
		return update("updateByPrimaryKeySelective", record);
	}
}
