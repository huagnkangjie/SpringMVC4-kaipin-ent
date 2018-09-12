package com.kaipin.oss.model.count;

import java.util.Map;

public class CountBase {
	
	private DeliveryPosition deliveryPosition;//职位投递详情
	
	private Map<String, Object> map;

	public DeliveryPosition getDeliveryPosition() {
		return deliveryPosition;
	}

	public void setDeliveryPosition(DeliveryPosition deliveryPosition) {
		this.deliveryPosition = deliveryPosition;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	

}
