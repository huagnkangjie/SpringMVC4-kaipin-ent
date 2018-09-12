package com.kaipin.enterprise.service.msg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.msg.MsgCompanyOffer;
import com.kaipin.enterprise.repository.dao.msg.IMsgCompanyOfferDao;
import com.kaipin.enterprise.service.msg.IMsgCompanyOfferService;

@Service("msgCompanyOfferService")
public class MsgCompanyOfferServiceImpl implements IMsgCompanyOfferService{
	
	@Autowired
	private IMsgCompanyOfferDao dao;

	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MsgCompanyOffer record) {
		return dao.insertSelective(record);
	}

	@Override
	public MsgCompanyOffer selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(MsgCompanyOffer record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public List<MsgCompanyOffer> getNeedSendOfferList() {
		return dao.getNeedSendOfferList() ;
	}

}
