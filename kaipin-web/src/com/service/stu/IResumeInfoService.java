package com.service.stu;

import com.model.stu.ResumeInfo;

public interface IResumeInfoService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(ResumeInfo record);

	public ResumeInfo selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(ResumeInfo record);
	
	public int deleteByStuUserId(String id);

}
