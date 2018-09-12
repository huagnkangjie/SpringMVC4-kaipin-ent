package com.enterprise.mapper.resume;

import java.util.List;

import com.enterprise.model.UserEducationBackground;

public interface UserEducationBackgroundMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserEducationBackground record);

    int insertSelective(UserEducationBackground record);

    UserEducationBackground selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserEducationBackground record);

    int updateByPrimaryKey(UserEducationBackground record);
    
    List<UserEducationBackground> selectByResumeId(String resumId);
}