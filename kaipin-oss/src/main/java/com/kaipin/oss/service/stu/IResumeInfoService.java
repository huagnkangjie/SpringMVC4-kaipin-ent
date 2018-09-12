package com.kaipin.oss.service.stu;

import com.kaipin.oss.model.stu.resume.ResumeInfo;

public interface IResumeInfoService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(ResumeInfo record);

	public ResumeInfo selectByPrimaryKey(String id);
	
	public ResumeInfo selectByUid(String uId);

	public int updateByPrimaryKeySelective(ResumeInfo record);
}
