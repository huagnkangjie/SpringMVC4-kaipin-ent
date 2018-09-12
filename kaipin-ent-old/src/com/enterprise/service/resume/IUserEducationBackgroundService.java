package com.enterprise.service.resume;


import java.util.List;

import com.enterprise.model.UserEducationBackground;


public interface IUserEducationBackgroundService {

	public UserEducationBackground getUserEducationBackground(String id);

	public void addUserEducationBackground(UserEducationBackground function);
	
	public int deleteByPrimaryKey(String id);
	
	public List<UserEducationBackground> selectByResumeId(String resumId);
}
