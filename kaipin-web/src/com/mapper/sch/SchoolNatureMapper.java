package com.mapper.sch;

import com.model.sch.SchoolNature;

public interface SchoolNatureMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolNature record);

    int insertSelective(SchoolNature record);

    SchoolNature selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolNature record);

    int updateByPrimaryKey(SchoolNature record);
}