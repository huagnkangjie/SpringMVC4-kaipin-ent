package com.enterprise.service.position.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.position.PositionDeliveryInterviewMapper;
import com.enterprise.mapper.position.PositionDeliveryMapper;
import com.enterprise.model.position.PositionDelivery;
import com.enterprise.model.position.PositionDeliveryInterview;
import com.enterprise.service.position.IPDeliveryService;

@Service("pDeliveryService")
@Repository
public class PDeliveryServiceImpl implements IPDeliveryService{
	@Autowired
	private PositionDeliveryMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(PositionDelivery record) {
		try {
			mapper.insert(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public PositionDelivery selectByPrimaryKey(String id) {
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int updateByPrimaryKeySelective(PositionDelivery record) {
		try {
			int i = mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
}
