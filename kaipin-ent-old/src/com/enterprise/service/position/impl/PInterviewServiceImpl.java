package com.enterprise.service.position.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.position.PositionDeliveryMapper;
import com.enterprise.mapper.position.PositionInterviewMapper;
import com.enterprise.model.position.PositionDelivery;
import com.enterprise.model.position.PositionInterview;
import com.enterprise.service.position.IPInterviewService;

@Service("pInterviewService")
@Repository
public class PInterviewServiceImpl implements IPInterviewService{
	
	@Autowired
	private PositionInterviewMapper mapper;
	
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
	public int insertSelective(PositionInterview record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public PositionInterview selectByPrimaryKey(String id) {
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int updateByPrimaryKeySelective(PositionInterview record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

}
