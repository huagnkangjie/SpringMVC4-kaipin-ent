package com.kaipin.oss.repository.dao.sch.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.sch.SchInfoFeedback;
import com.kaipin.oss.repository.dao.sch.ISchInfoFeedbackDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class SchInfoFeedbackDaoImpl extends MybatisBaseDAO<SchInfoFeedback, String> implements ISchInfoFeedbackDao{

	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.dao.sch.ISchInfoFeedbackDao";
	
	@Override
	public String getDefaultSqlNamespace() {
		return NAMESPACE_INFO;
	}

	@Override
	public boolean insertFeedback(SchInfoFeedback function) {
		boolean flag = false;
		try {
			this.insert("insertFeedback", function);
			return true;
		} catch (Exception e) {
			return flag;
		}
		
	}

	@Override
	public boolean insertFeedbackRelation(SchInfoFeedback function) {
		boolean flag = false;
		try {
			this.insert("insertFeedbackRelation", function);
			return true;
		} catch (Exception e) {
			return flag;
		}
	}

	@Override
	public List<SchInfoFeedback> getClassConfig() {
		return this.findListBy("getClassConfig");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchInfoFeedback> getClassTemplate(String configId){
		//return this.findListBy("getClassTemplate", function);
		return this.selectList("getClassTemplate", configId);
	}
}
