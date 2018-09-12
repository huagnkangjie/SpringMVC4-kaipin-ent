package com.mapper.sch;

import com.model.sch.SchoolInfo;

public interface SchoolInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolInfo record);

    int insertSelective(SchoolInfo record);

    SchoolInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolInfo record);

    int updateByPrimaryKey(SchoolInfo record);
}