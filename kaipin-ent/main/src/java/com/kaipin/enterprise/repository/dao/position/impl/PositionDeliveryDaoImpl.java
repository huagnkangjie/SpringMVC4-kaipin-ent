package com.kaipin.enterprise.repository.dao.position.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.position.PositionDelivery;
import com.kaipin.enterprise.repository.dao.position.IPositionDeliveryDao;

@Repository
public class PositionDeliveryDaoImpl extends MybatisBaseDAO<PositionDelivery, String> implements IPositionDeliveryDao{

	
	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.position.IPositionDeliveryDao";
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(PositionDelivery record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public PositionDelivery selectByPrimaryKey(String id) {
		try {
			return (PositionDelivery) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public int updateByPrimaryKeySelective(PositionDelivery record) {
		return this.update("updateByPrimaryKeySelective", record);
	}
}
