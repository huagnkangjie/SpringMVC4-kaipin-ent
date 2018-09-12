package com.kaipin.oss.repository.dao.stu;

import com.kaipin.oss.model.stu.resume.ResumeInfo;

public interface IResumeInfoDao {

	int deleteByPrimaryKey(String id);

    int insertSelective(ResumeInfo record);

    ResumeInfo selectByPrimaryKey(String id);
    
    ResumeInfo selectByUid(String uId);

    int updateByPrimaryKeySelective(ResumeInfo record);
}
