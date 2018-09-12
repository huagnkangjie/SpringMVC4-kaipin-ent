package com.enterprise.service.resume.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.resume.IMsgCompanyOfferDao;
import com.enterprise.model.resume.MsgCompanyOffer;
import com.enterprise.service.resume.IMsgCompanyOfferService;


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
