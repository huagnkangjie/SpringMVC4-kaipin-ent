package com.kaipin.enterprise.repository.dao.position.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.position.PositionInterview;
import com.kaipin.enterprise.repository.dao.position.IPositionInterviewDao;

@Repository
public class PositionInterviewDaoImpl extends MybatisBaseDAO<PositionInterview, String> implements IPositionInterviewDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.position.IPositionInterviewDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(PositionInterview record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public PositionInterview selectByPrimaryKey(String id) {
		try {
			return (PositionInterview) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public int updateByPrimaryKeySelective(PositionInterview record) {
		return this.update("updateByPrimaryKeySelective", record);
	}
}
