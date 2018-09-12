package com.kaipin.enterprise.repository.dao.msg.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.msg.MsgCompanyOffer;
import com.kaipin.enterprise.repository.dao.msg.IMsgCompanyOfferDao;

@Repository
public class MsgCompanyOfferImpl extends MybatisBaseDAO<MsgCompanyOffer, String> implements IMsgCompanyOfferDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.msg.IMsgCompanyOfferDao";
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
	public int insertSelective(MsgCompanyOffer record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public MsgCompanyOffer selectByPrimaryKey(String id) {
		try {
			
			return (MsgCompanyOffer) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKey(MsgCompanyOffer record) {
		try {
			this.update("updateByPrimaryKey", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MsgCompanyOffer> getNeedSendOfferList() {
		return this.selectList("getNeedSendOfferList");
	}

}
