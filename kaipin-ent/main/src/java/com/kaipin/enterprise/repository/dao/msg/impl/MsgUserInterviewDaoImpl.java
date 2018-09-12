package com.kaipin.enterprise.repository.dao.msg.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.msg.MsgUserInterview;
import com.kaipin.enterprise.repository.dao.msg.IMsgUserInterviewDao;

@Repository
public class MsgUserInterviewDaoImpl extends MybatisBaseDAO<MsgUserInterview, String> implements IMsgUserInterviewDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.msg.IMsgUserInterviewDao";
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
	public int insertSelective(MsgUserInterview record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public MsgUserInterview selectByPrimaryKey(String id) {
		try {
			
			return (MsgUserInterview) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserInterview record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
