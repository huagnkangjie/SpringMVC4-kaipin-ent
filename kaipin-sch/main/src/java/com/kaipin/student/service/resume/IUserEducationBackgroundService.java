package com.kaipin.student.service.resume;

import java.util.List;

import com.kaipin.student.model.resume.UserEducationBackground;

public interface IUserEducationBackgroundService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(UserEducationBackground record);

	public UserEducationBackground selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(UserEducationBackground record);
    
	public List<UserEducationBackground> selectByResumeId(String resumId);
}
