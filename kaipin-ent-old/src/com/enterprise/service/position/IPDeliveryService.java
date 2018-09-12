package com.enterprise.service.position;

import com.enterprise.model.position.PositionDelivery;

public interface IPDeliveryService {
	
	public int deleteByPrimaryKey(String id);

	public int insertSelective(PositionDelivery record);

	public PositionDelivery selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(PositionDelivery record);

}
