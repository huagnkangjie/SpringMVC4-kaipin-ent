package com.enterprise.mapper.resume;

import com.enterprise.model.resume.ResumeInfo;

public interface ResumeInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResumeInfo record);

    int insertSelective(ResumeInfo record);

    ResumeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResumeInfo record);

    int updateByPrimaryKey(ResumeInfo record);
}