package com.enterprise.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.common.MsgUserInterviewMapper;
import com.enterprise.model.common.MsgUserInterview;
import com.enterprise.service.common.IMsgUserInterviewService;

@Service("msgUserInterviewService")
@Repository
public class MsgUserInterviewServiceImpl implements IMsgUserInterviewService{
	
	@Autowired
	private MsgUserInterviewMapper mapper;

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
	public int insertSelective(MsgUserInterview record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public MsgUserInterview selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserInterview record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
