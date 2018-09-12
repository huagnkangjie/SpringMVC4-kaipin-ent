package com.enterprise.mapper.position;

import com.enterprise.model.position.PositionInterview;

public interface PositionInterviewMapper {
    int deleteByPrimaryKey(String id);

    int insert(PositionInterview record);

    int insertSelective(PositionInterview record);

    PositionInterview selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionInterview record);

    int updateByPrimaryKey(PositionInterview record);
}