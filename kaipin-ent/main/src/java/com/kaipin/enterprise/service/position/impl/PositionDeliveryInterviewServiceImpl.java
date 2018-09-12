package com.kaipin.enterprise.service.position.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.position.PositionDeliveryInterview;
import com.kaipin.enterprise.repository.dao.position.IPositionDeliveryInterviewDao;
import com.kaipin.enterprise.service.position.IPDeliveryInterviewService;

@Service("positionDeliveryInterviewService")
public class PositionDeliveryInterviewServiceImpl implements IPDeliveryInterviewService{

	@Autowired
	private IPositionDeliveryInterviewDao dao;
	
	@Override
	public int deleteByPrimaryKey(String positionDeliveryId) {
		return dao.deleteByPrimaryKey(positionDeliveryId);
	}

	@Override
	public int insertSelective(PositionDeliveryInterview record) {
		return dao.insertSelective(record);
	}

	@Override
	public PositionDeliveryInterview selectByPrimaryKey(String positionDeliveryId) {
		return dao.selectByPrimaryKey(positionDeliveryId);
	}

	@Override
	public int updateByPrimaryKeySelective(PositionDeliveryInterview record) {
		return dao.updateByPrimaryKeySelective(record);
	}

}
