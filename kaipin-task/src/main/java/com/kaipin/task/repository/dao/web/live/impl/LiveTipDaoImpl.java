package com.kaipin.task.repository.dao.web.live.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.task.repository.dao.web.live.ILiveTipDao;
import com.kaipin.task.repository.mybatis.dao.MybatisBaseDAO;

@Repository
@SuppressWarnings("unchecked")
public class LiveTipDaoImpl extends MybatisBaseDAO<Object, String> implements ILiveTipDao{

	
	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.task.repository.dao.web.live.ILiveTipDao";
	}
	
	
	@Override
	public List<Map<String, Object>> getLiveTipList() {
		return this.selectList("getLiveTipList");
	}


	@Override
	public void insertPhoneTip(Map<String, Object> map) {
		this.insert("insertPhoneTip", map);
	}

}
