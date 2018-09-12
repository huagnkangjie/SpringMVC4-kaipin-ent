package com.mapper.sch;

import com.model.sch.SchoolType;

public interface SchoolTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolType record);

    int insertSelective(SchoolType record);

    SchoolType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolType record);

    int updateByPrimaryKey(SchoolType record);
}