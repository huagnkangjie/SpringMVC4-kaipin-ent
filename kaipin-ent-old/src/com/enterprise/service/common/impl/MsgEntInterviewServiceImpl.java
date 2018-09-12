package com.enterprise.service.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.common.MsgEntInterviewMapper;
import com.enterprise.model.common.MsgEntInterview;
import com.enterprise.service.common.IMsgEntInterviewService;

@Service("msgEntInterviewService")
@Repository
public class MsgEntInterviewServiceImpl implements IMsgEntInterviewService{
	
	@Autowired
	private MsgEntInterviewMapper mapper;

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
	public int insertSelective(MsgEntInterview record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public MsgEntInterview selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgEntInterview record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> getMsgEntViewList(Map<String, Object> map) {
		return mapper.getMsgEntViewList(map);
	}

	@Override
	public int insertList(List<MsgEntInterview> list) {
		for (int i = 0; i < list.size(); i++) {
			mapper.insertSelective(list.get(i));
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> getMsgEntViewtCount(Map<String, Object> map) {
		return mapper.getMsgEntViewCount(map);
	}

	@Override
	public MsgEntInterview getMsgEntViewById(String id) {
		return mapper.getMsgEntViewById(id);
	}

	@Override
	public int updateStatusById(String id) {
		try {
			mapper.updateStatusById(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
