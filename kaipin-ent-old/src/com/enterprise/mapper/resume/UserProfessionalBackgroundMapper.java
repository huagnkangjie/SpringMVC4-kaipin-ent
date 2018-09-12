package com.enterprise.mapper.resume;

import java.util.List;

import com.enterprise.model.UserProfessionalBackground;

public interface UserProfessionalBackgroundMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserProfessionalBackground record);

    int insertSelective(UserProfessionalBackground record);

    UserProfessionalBackground selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserProfessionalBackground record);

    int updateByPrimaryKey(UserProfessionalBackground record);
    
    List<UserProfessionalBackground> selectByResumeId(String resumId);
}