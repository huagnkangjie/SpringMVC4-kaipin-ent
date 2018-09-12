package com.kaipin.student.repository.dao.resume.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.student.model.resume.UserResumeRelation;
import com.kaipin.student.repository.dao.resume.IUserResumeRelationDao;

@Repository
public class UserResumeRelationImpl extends MybatisBaseDAO<UserResumeRelation, String> implements IUserResumeRelationDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.student.repository.dao.resume.IUserResumeRelationDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(UserResumeRelation record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public UserResumeRelation selectByPrimaryKey(String id) {
		return (UserResumeRelation) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserResumeRelation record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateStatus(Map<String, String> map) {
		return this.update("updateStatus", map);
	}
}
