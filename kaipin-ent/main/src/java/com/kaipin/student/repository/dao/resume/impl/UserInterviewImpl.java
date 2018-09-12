package com.kaipin.student.repository.dao.resume.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.student.model.resume.UserInterview;
import com.kaipin.student.repository.dao.resume.IUserInterviewDao;

@Repository
public class UserInterviewImpl extends MybatisBaseDAO<UserInterview, String> implements IUserInterviewDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.student.repository.dao.resume.IUserInterviewDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(UserInterview record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public UserInterview selectByPrimaryKey(String id) {
		return (UserInterview) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInterview record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInterview> getLog(Map<String, Object> map) {
		return this.selectList("getLog", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getMsg(Map<String, Object> map) {
		return this.selectList("getMsg", map);
	}
}
