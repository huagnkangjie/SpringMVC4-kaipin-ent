package com.kaipin.student.service.resume.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.model.resume.UserEducationBackground;
import com.kaipin.student.repository.dao.resume.IUserEducationBackgroundDao;
import com.kaipin.student.service.resume.IUserEducationBackgroundService;

@Service("userEducationBackgroundService")
public class UserEducationBackgroundServiceImpl implements IUserEducationBackgroundService{

	@Autowired
	private IUserEducationBackgroundDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserEducationBackground record) {
		return dao.insertSelective(record);
	}

	@Override
	public UserEducationBackground selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserEducationBackground record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserEducationBackground> selectByResumeId(String resumId) {
		return this.selectByResumeId(resumId);
	}
	
}
