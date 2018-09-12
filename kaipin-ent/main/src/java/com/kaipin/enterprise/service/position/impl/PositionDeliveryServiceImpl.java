package com.kaipin.enterprise.service.position.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.position.PositionDelivery;
import com.kaipin.enterprise.repository.dao.position.IPositionDeliveryDao;
import com.kaipin.enterprise.service.position.IPDeliveryService;

@Service("positionDeliveryService")
public class PositionDeliveryServiceImpl implements IPDeliveryService{

	@Autowired
	private IPositionDeliveryDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			dao.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(PositionDelivery record) {
		try {
			dao.insertSelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public PositionDelivery selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PositionDelivery record) {
		try {
			dao.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
