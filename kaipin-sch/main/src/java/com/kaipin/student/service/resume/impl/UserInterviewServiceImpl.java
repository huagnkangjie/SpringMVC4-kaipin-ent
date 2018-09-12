package com.kaipin.student.service.resume.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.model.resume.UserInterview;
import com.kaipin.student.repository.dao.resume.IUserInterviewDao;
import com.kaipin.student.service.resume.IUserInterviewService;

@Service("userInterviewService")
public class UserInterviewServiceImpl implements IUserInterviewService{

	@Autowired
	private IUserInterviewDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserInterview record) {
		return dao.insertSelective(record);
	}

	@Override
	public UserInterview selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInterview record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserInterview> getLog(Map<String, Object> map) {
		return dao.getLog(map);
	}

	@Override
	public List<Map<String, Object>> getMsg(Map<String, Object> map) {
		return dao.getMsg(map);
	}

}
