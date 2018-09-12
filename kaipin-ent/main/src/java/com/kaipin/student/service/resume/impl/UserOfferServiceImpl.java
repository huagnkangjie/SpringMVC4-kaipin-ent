package com.kaipin.student.service.resume.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.model.resume.UserOffer;
import com.kaipin.student.repository.dao.resume.IUserOfferDao;
import com.kaipin.student.service.resume.IUserOfferService;

@Service("userOfferService")
public class UserOfferServiceImpl implements IUserOfferService{

	@Autowired
	private IUserOfferDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserOffer record) {
		return dao.insertSelective(record);
	}

	@Override
	public UserOffer selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserOffer record) {
		return dao.updateByPrimaryKeySelective(record);
	}

}
