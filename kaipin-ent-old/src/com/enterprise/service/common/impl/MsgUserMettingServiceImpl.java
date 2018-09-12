package com.enterprise.service.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.common.MsgUserMettingMapper;
import com.enterprise.model.common.MsgUserMetting;
import com.enterprise.service.common.IMsgUserMettingService;

@Service("msgUserMettingService")
@Repository
public class MsgUserMettingServiceImpl implements IMsgUserMettingService{
	
	@Autowired
	private MsgUserMettingMapper mapper;

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
	public int insertSelective(MsgUserMetting record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public MsgUserMetting selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserMetting record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> msgMeetInsVal(Map<String, Object> map) {
		return mapper.msgMeetInsVal(map);
	}

	@Override
	public List<Map<String, Object>> msgMeetList(Map<String, Object> map) {
		return mapper.msgMeetList(map);
	}

	@Override
	public int insertMsgList(List<MsgUserMetting> listVal) {
		for (int i = 0; i < listVal.size(); i++) {
			mapper.insert(listVal.get(i));
		}
		return 0;
	}

}
