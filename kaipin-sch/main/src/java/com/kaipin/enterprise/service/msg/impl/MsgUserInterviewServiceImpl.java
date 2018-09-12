package com.kaipin.enterprise.service.msg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.msg.MsgUserInterview;
import com.kaipin.enterprise.repository.dao.msg.IMsgUserInterviewDao;
import com.kaipin.enterprise.service.msg.IMsgUserInterviewService;

@Service("msgUserInterviewService")
public class MsgUserInterviewServiceImpl implements IMsgUserInterviewService{

	@Autowired
	private IMsgUserInterviewDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MsgUserInterview record) {
		return dao.insertSelective(record);
	}

	@Override
	public MsgUserInterview selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserInterview record) {
		return dao.updateByPrimaryKeySelective(record);
	}

}
