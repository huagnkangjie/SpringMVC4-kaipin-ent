package com.kaipin.enterprise.repository.dao.msg.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.msg.MsgUserInterview;
import com.kaipin.enterprise.model.msg.MsgUserOffer;
import com.kaipin.enterprise.repository.dao.msg.IMsgUserOfferDao;

@Repository
public class MsgUserOfferDaoImpl extends MybatisBaseDAO<MsgUserOffer, String> implements IMsgUserOfferDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.msg.IMsgUserOfferDao";
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			this.delete("deleteByPrimaryKey", id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(MsgUserOffer record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public MsgUserOffer selectByPrimaryKey(String id) {
		try {
			
			return (MsgUserOffer) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserOffer record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}


}
