package com.enterprise.service.resume.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.resume.UserProfessionalBackgroundMapper;
import com.enterprise.model.UserProfessionalBackground;
import com.enterprise.service.resume.IUserProfessionalBackgroundService;

@Service("userProfessionalBackgroundService")
@Repository
public class UserProfessionalBackgroundServiceImpl implements IUserProfessionalBackgroundService{
	
	@Autowired
	private UserProfessionalBackgroundMapper mapper;

	@Override
	public UserProfessionalBackground getUserProfessionalBackground(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void addUserProfessionalBackground(
			UserProfessionalBackground function) {
		mapper.insertSelective(function);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<UserProfessionalBackground> selectByResumeId(String resumId) {
		return mapper.selectByResumeId(resumId);
	}
	

}
