package com.kaipin.enterprise.service.msg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.msg.MsgUserOffer;
import com.kaipin.enterprise.repository.dao.msg.IMsgUserOfferDao;
import com.kaipin.enterprise.service.msg.IMsgUserOfferService;

@Service("msgUserOfferService")
public class MsgUserOfferServiceImpl implements IMsgUserOfferService{

	@Autowired
	private IMsgUserOfferDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MsgUserOffer record) {
		return dao.insertSelective(record);
	}

	@Override
	public MsgUserOffer selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserOffer record) {
		return dao.updateByPrimaryKeySelective(record);
	}

}
