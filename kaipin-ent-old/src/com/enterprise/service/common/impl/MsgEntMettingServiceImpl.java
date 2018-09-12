package com.enterprise.service.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.common.MsgEntMettingMapper;
import com.enterprise.model.common.MsgEntMetting;
import com.enterprise.service.common.IMsgEntMettingService;

@Service("msgEntMettingService")
@Repository
public class MsgEntMettingServiceImpl implements IMsgEntMettingService{

	@Autowired
	private MsgEntMettingMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(MsgEntMetting record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public MsgEntMetting selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgEntMetting record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public int insertEntMsgList(List<MsgEntMetting> list) {
		for (int i = 0; i < list.size(); i++) {
			mapper.insertSelective(list.get(i));
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> msgEntMeetList(Map<String, Object> map) {
		return mapper.msgEntMeetList(map);
	}

	@Override
	public List<Map<String, Object>> getMsgEntMeetCount(Map<String, Object> map) {
		return mapper.getMsgEntMeetCount(map);
	}

}
