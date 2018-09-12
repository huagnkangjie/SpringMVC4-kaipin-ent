package com.kaipin.enterprise.service.position.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.position.PositionInterview;
import com.kaipin.enterprise.repository.dao.position.IPositionInterviewDao;
import com.kaipin.enterprise.service.position.IPInterviewService;

@Service("positionInterviewService")
public class PositionInterviewServiceImpl implements IPInterviewService{

	@Autowired
	private IPositionInterviewDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(PositionInterview record) {
		return dao.insertSelective(record);
	}

	@Override
	public PositionInterview selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PositionInterview record) {
		return dao.updateByPrimaryKeySelective(record);
	}

}
