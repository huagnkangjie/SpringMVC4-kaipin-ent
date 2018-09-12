package com.kaipin.enterprise.repository.dao.position;

import com.kaipin.enterprise.model.position.PositionDeliveryInterview;

public interface IPositionDeliveryInterviewDao {
    int deleteByPrimaryKey(String positionDeliveryId);

    int insertSelective(PositionDeliveryInterview record);

    PositionDeliveryInterview selectByPrimaryKey(String positionDeliveryId);

    int updateByPrimaryKeySelective(PositionDeliveryInterview record);

}