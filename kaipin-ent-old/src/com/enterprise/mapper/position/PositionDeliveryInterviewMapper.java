package com.enterprise.mapper.position;

import com.enterprise.model.position.PositionDeliveryInterview;

public interface PositionDeliveryInterviewMapper {
    int deleteByPrimaryKey(String positionDeliveryId);

    int insert(PositionDeliveryInterview record);

    int insertSelective(PositionDeliveryInterview record);

    PositionDeliveryInterview selectByPrimaryKey(String positionDeliveryId);

    int updateByPrimaryKeySelective(PositionDeliveryInterview record);

    int updateByPrimaryKey(PositionDeliveryInterview record);
}