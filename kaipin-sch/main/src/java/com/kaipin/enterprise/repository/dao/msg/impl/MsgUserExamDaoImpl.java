package com.kaipin.enterprise.repository.dao.msg.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.msg.MsgUserExam;
import com.kaipin.enterprise.repository.dao.msg.IMsgUserExamDao;

@Repository
public class MsgUserExamDaoImpl extends MybatisBaseDAO<MsgUserExam, String> implements IMsgUserExamDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.msg.IMsgUserExamDao";
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
	public int insertSelective(MsgUserExam record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public MsgUserExam selectByPrimaryKey(String id) {
		try {
			
			return (MsgUserExam) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(MsgUserExam record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
