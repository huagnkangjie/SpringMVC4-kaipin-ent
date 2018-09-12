package com.kaipin.student.service.resume;

import java.util.List;

import com.kaipin.student.model.resume.UserProfessionalBackground;

public interface IUserProfessionalBackgroundService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(UserProfessionalBackground record);

	public UserProfessionalBackground selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(UserProfessionalBackground record);
    
	public List<UserProfessionalBackground> selectByResumeId(String resumId);
}
