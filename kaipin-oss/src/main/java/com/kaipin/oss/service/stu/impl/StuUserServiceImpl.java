package com.kaipin.oss.service.stu.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.model.stu.StuUser;
import com.kaipin.oss.repository.dao.stu.IStuUserDao;
import com.kaipin.oss.service.stu.IStuUserService;


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
