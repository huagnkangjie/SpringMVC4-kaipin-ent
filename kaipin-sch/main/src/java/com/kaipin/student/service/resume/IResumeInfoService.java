package com.kaipin.student.service.resume;

import com.kaipin.student.model.resume.ResumeInfo;

public interface IResumeInfoService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(ResumeInfo record);

	public ResumeInfo selectByPrimaryKey(String id);
	
	public ResumeInfo selectByUid(String uId);

	public int updateByPrimaryKeySelective(ResumeInfo record);
}
