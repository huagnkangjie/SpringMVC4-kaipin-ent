package com.enterprise.service.resume.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.resume.UserEducationBackgroundMapper;
import com.enterprise.model.UserEducationBackground;
import com.enterprise.service.resume.IUserEducationBackgroundService;

@Service("userEducationBackgroundService")
@Repository
public class UserEducationBackgroundServiceImpl implements IUserEducationBackgroundService{

	@Autowired
	private UserEducationBackgroundMapper mapper;

	@Override
	public UserEducationBackground getUserEducationBackground(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void addUserEducationBackground(UserEducationBackground function) {
		mapper.insertSelective(function);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<UserEducationBackground> selectByResumeId(String resumId) {
		return mapper.selectByResumeId(resumId);
	}
}
