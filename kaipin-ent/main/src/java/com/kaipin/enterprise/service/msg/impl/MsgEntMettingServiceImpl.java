package com.kaipin.enterprise.service.msg.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.msg.MsgEntMetting;
import com.kaipin.enterprise.repository.dao.msg.IMsgEntMettingDao;
import com.kaipin.enterprise.service.msg.IMsgEntMettingService;

@Service("msgEntMettingService")
public class MsgEntMettingServiceImpl implements IMsgEntMettingService{

	@Autowired
	private IMsgEntMettingDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MsgEntMetting record) {
		return dao.insertSelective(record);
	}

	@Override
	public MsgEntMetting selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgEntMetting record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertEntMsgList(List<MsgEntMetting> list) {
		return dao.insertEntMsgList(list);
	}

	@Override
	public List<Map<String, Object>> msgEntMeetList(Map<String, Object> map) {
		return dao.msgEntMeetList(map);
	}

	@Override
	public List<Map<String, Object>> getMsgEntMeetCount(Map<String, Object> map) {
		return dao.getMsgEntMeetCount(map);
	}

}
