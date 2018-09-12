package com.kaipin.enterprise.repository.dao.msg.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.msg.MsgEntMetting;
import com.kaipin.enterprise.repository.dao.msg.IMsgEntMettingDao;

@SuppressWarnings("unchecked")
@Repository
public class MsgEntMettingDaoImpl extends MybatisBaseDAO<MsgEntMetting, String> implements IMsgEntMettingDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.msg.IMsgEntMettingDao";
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
	public int insertSelective(MsgEntMetting record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public MsgEntMetting selectByPrimaryKey(String id) {
		try {
			
			return (MsgEntMetting) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(MsgEntMetting record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int insertEntMsgList(List<MsgEntMetting> list) {
		for (int i = 0; i < list.size(); i++) {
			this.insertSelective(list.get(i));
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> msgEntMeetList(Map<String, Object> map) {
		return this.selectList("msgEntMeetList", map);
	}

	@Override
	public List<Map<String, Object>> getMsgEntMeetCount(Map<String, Object> map) {
		return this.selectList("getMsgEntMeetCount", map);
	}

}
