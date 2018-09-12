package com.enterprise.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.common.MsgUserOfferMapper;
import com.enterprise.model.common.MsgUserOffer;
import com.enterprise.service.common.IMsgUserOfferService;

@Service("msgUserOfferService")
@Repository
public class MsgUserOfferServiceImpl implements IMsgUserOfferService{
	
	@Autowired
	private MsgUserOfferMapper mapper;

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
	public int insertSelective(MsgUserOffer record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public MsgUserOffer selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserOffer record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
