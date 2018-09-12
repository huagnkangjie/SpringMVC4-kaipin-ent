package com.kaipin.enterprise.service.msg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.msg.MsgUserExam;
import com.kaipin.enterprise.repository.dao.msg.IMsgUserExamDao;
import com.kaipin.enterprise.service.msg.IMsgUserExamService;

@Service("msgUserExamService")
public class MsgUserExamServiceImpl implements IMsgUserExamService{

	@Autowired
	private IMsgUserExamDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MsgUserExam record) {
		return dao.insertSelective(record);
	}

	@Override
	public MsgUserExam selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserExam record) {
		return dao.updateByPrimaryKeySelective(record);
	}

}
