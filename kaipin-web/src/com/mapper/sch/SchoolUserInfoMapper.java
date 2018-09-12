package com.mapper.sch;

import com.model.sch.SchoolUserInfo;

public interface SchoolUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolUserInfo record);

    int insertSelective(SchoolUserInfo record);

    SchoolUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolUserInfo record);

    int updateByPrimaryKey(SchoolUserInfo record);
}