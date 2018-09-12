package com.kaipin.student.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.model.user.StuUser;
import com.kaipin.student.repository.dao.user.IStuUserDao;
import com.kaipin.student.service.user.IStuUserService;

@Service("stuUserService")
public class StuUserServiceImpl implements IStuUserService {
	
	@Autowired
	private IStuUserDao dao;

	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(StuUser record) {
		return dao.insertSelective(record);
	}

	@Override
	public StuUser selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(StuUser record) {
		return dao.updateByPrimaryKeySelective(record);
	}

}
