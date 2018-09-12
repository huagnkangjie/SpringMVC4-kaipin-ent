package com.kaipin.student.repository.dao.resume.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.student.model.resume.UserEducationBackground;
import com.kaipin.student.repository.dao.resume.IUserEducationBackgroundDao;

@Repository
public class UserEducationBackgroundImpl extends MybatisBaseDAO<UserEducationBackground, String> implements IUserEducationBackgroundDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.student.repository.dao.resume.IUserEducationBackgroundDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(UserEducationBackground record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public UserEducationBackground selectByPrimaryKey(String id) {
		return (UserEducationBackground) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserEducationBackground record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEducationBackground> selectByResumeId(String resumId) {
		return this.selectList("selectByResumeId", resumId);
	}


}
