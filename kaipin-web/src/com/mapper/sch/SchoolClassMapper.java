package com.mapper.sch;

import com.model.sch.SchoolClass;

public interface SchoolClassMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolClass record);

    int insertSelective(SchoolClass record);

    SchoolClass selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolClass record);

    int updateByPrimaryKey(SchoolClass record);
}