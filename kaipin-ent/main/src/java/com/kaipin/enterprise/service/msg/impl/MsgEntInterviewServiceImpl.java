package com.kaipin.enterprise.service.msg.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.msg.MsgEntInterview;
import com.kaipin.enterprise.repository.dao.msg.IMsgEntInterviewDao;
import com.kaipin.enterprise.service.msg.IMsgEntInterviewService;

@Service("msgEntInterviewService")
public class MsgEntInterviewServiceImpl implements IMsgEntInterviewService{

	@Autowired
	private IMsgEntInterviewDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MsgEntInterview record) {
		return dao.insertSelective(record);
	}

	@Override
	public MsgEntInterview selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgEntInterview record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Map<String, Object>> getMsgEntViewList(Map<String, Object> map) {
		return dao.getMsgEntViewList(map);
	}

	@Override
	public List<Map<String, Object>> getMsgEntViewtCount(Map<String, Object> map) {
		return dao.getMsgEntViewtCount(map);
	}

	@Override
	public int insertList(List<MsgEntInterview> list) {
		return dao.insertList(list);
	}

	@Override
	public MsgEntInterview getMsgEntViewById(String id) {
		return dao.getMsgEntViewById(id);
	}

	@Override
	public int updateStatusById(String id) {
		return dao.updateStatusById(id);
	}

}
