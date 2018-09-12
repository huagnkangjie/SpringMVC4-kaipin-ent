package com.enterprise.service.resume;

import java.util.List;

import com.enterprise.model.UserProfessionalBackground;

public interface IUserProfessionalBackgroundService {

	public UserProfessionalBackground getUserProfessionalBackground(String id);

	public void addUserProfessionalBackground(UserProfessionalBackground function);
	
	public int deleteByPrimaryKey(String id);
	
	public List<UserProfessionalBackground> selectByResumeId(String resumId);
}
