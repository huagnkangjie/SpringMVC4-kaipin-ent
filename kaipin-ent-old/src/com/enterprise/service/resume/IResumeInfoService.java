package com.enterprise.service.resume;

import com.enterprise.model.resume.ResumeInfo;

public interface IResumeInfoService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(ResumeInfo record);

	public ResumeInfo selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(ResumeInfo record);

}
