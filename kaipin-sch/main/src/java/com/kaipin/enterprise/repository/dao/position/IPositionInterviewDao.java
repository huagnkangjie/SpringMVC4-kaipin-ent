package com.kaipin.enterprise.repository.dao.position;

import com.kaipin.enterprise.model.position.PositionInterview;

public interface IPositionInterviewDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(PositionInterview record);

    PositionInterview selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionInterview record);
}