package com.kaipin.enterprise.repository.dao.position.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.position.PositionDeliveryInterview;
import com.kaipin.enterprise.repository.dao.position.IPositionDeliveryInterviewDao;

@Repository
public class PositionDeliveryInterviewDaoImpl extends MybatisBaseDAO<PositionDeliveryInterview, String> implements IPositionDeliveryInterviewDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.position.IPositionDeliveryInterviewDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(PositionDeliveryInterview record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public PositionDeliveryInterview selectByPrimaryKey(String id) {
		try {
			return (PositionDeliveryInterview) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public int updateByPrimaryKeySelective(PositionDeliveryInterview record) {
		return this.update("updateByPrimaryKeySelective", record);
	}
}
