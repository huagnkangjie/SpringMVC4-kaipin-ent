package com.kaipin.student.repository.dao.resume;

import java.util.List;

import com.kaipin.student.model.resume.UserEducationBackground;


public interface IUserEducationBackgroundDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(UserEducationBackground record);

    UserEducationBackground selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserEducationBackground record);
    
    List<UserEducationBackground> selectByResumeId(String resumId);
}