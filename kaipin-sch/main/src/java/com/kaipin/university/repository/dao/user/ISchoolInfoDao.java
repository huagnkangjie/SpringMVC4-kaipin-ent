package com.kaipin.university.repository.dao.user;

import com.kaipin.university.model.user.SchoolInfo;

public interface ISchoolInfoDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(SchoolInfo record);

    SchoolInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolInfo record);

}