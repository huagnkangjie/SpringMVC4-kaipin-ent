package com.kaipin.student.service.resume.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.model.resume.UserResumeRelation;
import com.kaipin.student.repository.dao.resume.IUserResumeRelationDao;
import com.kaipin.student.service.resume.IUserResumeRelationService;

@Service("userResumeRelationService")
public class UserResumeRelationServiceImpl implements IUserResumeRelationService{

	@Autowired
	private IUserResumeRelationDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserResumeRelation record) {
		return dao.insertSelective(record);
	}

	@Override
	public UserResumeRelation selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserResumeRelation record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateStatus(Map<String, String> map) {
		return dao.updateStatus(map);
	}

}
