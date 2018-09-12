package com.kaipin.student.repository.dao.resume.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.student.model.resume.UserProfessionalBackground;
import com.kaipin.student.repository.dao.resume.IUserProfessionalBackgroundDao;

@Repository
public class UserProfessionalBackgroundImpl extends MybatisBaseDAO<UserProfessionalBackground, String> implements IUserProfessionalBackgroundDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.student.repository.dao.resume.IUserProfessionalBackgroundDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(UserProfessionalBackground record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public UserProfessionalBackground selectByPrimaryKey(String id) {
		return (UserProfessionalBackground) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserProfessionalBackground record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfessionalBackground> selectByResumeId(String resumId) {
		return this.selectList("selectByResumeId", resumId);
	}
}
