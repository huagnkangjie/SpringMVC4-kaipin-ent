package com.enterprise.mapper.position;

import com.enterprise.model.position.PositionDelivery;

public interface PositionDeliveryMapper {
    int deleteByPrimaryKey(String id);

    int insert(PositionDelivery record);

    int insertSelective(PositionDelivery record);

    PositionDelivery selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionDelivery record);

    int updateByPrimaryKey(PositionDelivery record);
}