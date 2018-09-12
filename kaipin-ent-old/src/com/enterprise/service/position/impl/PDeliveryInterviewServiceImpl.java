package com.enterprise.service.position.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.position.PositionDeliveryInterviewMapper;
import com.enterprise.model.PositionInfo;
import com.enterprise.model.position.PositionDeliveryInterview;
import com.enterprise.service.position.IPDeliveryInterviewService;

@Service("pDeliveryInterviewService")
@Repository
public class PDeliveryInterviewServiceImpl implements IPDeliveryInterviewService{

	@Autowired
	private PositionDeliveryInterviewMapper mapper;
	
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
	public int insertSelective(PositionDeliveryInterview record) {
		try {
			mapper.insert(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public PositionDeliveryInterview selectByPrimaryKey(String id) {
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int updateByPrimaryKeySelective(PositionDeliveryInterview record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
}
