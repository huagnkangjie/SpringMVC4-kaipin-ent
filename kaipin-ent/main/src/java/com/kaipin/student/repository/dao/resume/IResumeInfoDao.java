package com.kaipin.student.repository.dao.resume;

import com.kaipin.student.model.resume.ResumeInfo;

public interface IResumeInfoDao {

	int deleteByPrimaryKey(String id);

    int insertSelective(ResumeInfo record);

    ResumeInfo selectByPrimaryKey(String id);
    
    ResumeInfo selectByUid(String uId);

    int updateByPrimaryKeySelective(ResumeInfo record);
}
