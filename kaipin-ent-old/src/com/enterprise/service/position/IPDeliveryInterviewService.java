package com.enterprise.service.position;

import com.enterprise.model.position.PositionDeliveryInterview;

public interface IPDeliveryInterviewService {
	
	public int deleteByPrimaryKey(String positionDeliveryId);


	public int insertSelective(PositionDeliveryInterview record);

	public PositionDeliveryInterview selectByPrimaryKey(String positionDeliveryId);

	public int updateByPrimaryKeySelective(PositionDeliveryInterview record);


}
