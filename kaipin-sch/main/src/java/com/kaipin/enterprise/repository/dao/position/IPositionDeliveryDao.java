package com.kaipin.enterprise.repository.dao.position;

import com.kaipin.enterprise.model.position.PositionDelivery;

public interface IPositionDeliveryDao {

	int deleteByPrimaryKey(String id);

    int insertSelective(PositionDelivery record);

    PositionDelivery selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionDelivery record);
}
