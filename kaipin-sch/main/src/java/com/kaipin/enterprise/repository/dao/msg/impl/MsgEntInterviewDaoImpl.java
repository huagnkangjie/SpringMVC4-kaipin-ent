package com.kaipin.enterprise.repository.dao.msg.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.msg.MsgEntInterview;
import com.kaipin.enterprise.repository.dao.msg.IMsgEntInterviewDao;

@SuppressWarnings("unchecked")
@Repository
public class MsgEntInterviewDaoImpl extends MybatisBaseDAO<MsgEntInterview, String> implements IMsgEntInterviewDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.msg.IMsgEntInterviewDao";
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
	public int insertSelective(MsgEntInterview record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public MsgEntInterview selectByPrimaryKey(String id) {
		try {
			
			return (MsgEntInterview) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(MsgEntInterview record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> getMsgEntViewList(Map<String, Object> map) {
		return this.selectList("getMsgEntViewList", map);
	}

	@Override
	public List<Map<String, Object>> getMsgEntViewtCount(Map<String, Object> map) {
		return this.selectList("getMsgEntViewCount", map);
	}

	@Override
	public int insertList(List<MsgEntInterview> list) {
		for (int i = 0; i < list.size(); i++) {
			this.insertSelective(list.get(i));
		}
		return 0;
	}

	@Override
	public MsgEntInterview getMsgEntViewById(String id) {
		return (MsgEntInterview) this.selectOne("getMsgEntViewById", id);
	}

	@Override
	public int updateStatusById(String id) {
		return this.update("updateStatusById", id);
	}

}
