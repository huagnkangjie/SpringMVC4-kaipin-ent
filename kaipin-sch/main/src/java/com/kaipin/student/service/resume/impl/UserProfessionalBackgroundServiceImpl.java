package com.kaipin.student.service.resume.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.model.resume.UserProfessionalBackground;
import com.kaipin.student.repository.dao.resume.IUserProfessionalBackgroundDao;
import com.kaipin.student.service.resume.IUserProfessionalBackgroundService;

@Service("userProfessionalBackgroundService")
public class UserProfessionalBackgroundServiceImpl implements IUserProfessionalBackgroundService{

	@Autowired
	private IUserProfessionalBackgroundDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserProfessionalBackground record) {
		return dao.insertSelective(record);
	}

	@Override
	public UserProfessionalBackground selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserProfessionalBackground record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserProfessionalBackground> selectByResumeId(String resumId) {
		return dao.selectByResumeId(resumId);
	}

}
