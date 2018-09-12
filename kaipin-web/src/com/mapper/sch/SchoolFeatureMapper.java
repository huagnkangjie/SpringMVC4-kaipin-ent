package com.mapper.sch;

import com.model.sch.SchoolFeature;

public interface SchoolFeatureMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolFeature record);

    int insertSelective(SchoolFeature record);

    SchoolFeature selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolFeature record);

    int updateByPrimaryKey(SchoolFeature record);
}