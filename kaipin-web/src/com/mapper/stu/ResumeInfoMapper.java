package com.mapper.stu;

import com.model.stu.ResumeInfo;

public interface ResumeInfoMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(ResumeInfo record);

    ResumeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResumeInfo record);
    
    int deleteByStuUserId(String id);

}