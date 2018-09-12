package com.kaipin.student.repository.dao.resume;

import java.util.List;

import com.kaipin.student.model.resume.UserProfessionalBackground;


public interface IUserProfessionalBackgroundDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(UserProfessionalBackground record);

    UserProfessionalBackground selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserProfessionalBackground record);
    
    List<UserProfessionalBackground> selectByResumeId(String resumId);
}