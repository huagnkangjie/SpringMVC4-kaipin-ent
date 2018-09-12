package com.mapper.sch;

import com.model.sch.SchoolInfoLink;

public interface SchoolInfoLinkMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolInfoLink record);

    int insertSelective(SchoolInfoLink record);

    SchoolInfoLink selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolInfoLink record);

    int updateByPrimaryKey(SchoolInfoLink record);
}