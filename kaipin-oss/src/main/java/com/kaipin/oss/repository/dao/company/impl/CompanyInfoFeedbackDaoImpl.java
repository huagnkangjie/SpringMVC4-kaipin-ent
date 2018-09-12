package com.kaipin.oss.repository.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.company.CompanyInfoFeedback;
import com.kaipin.oss.repository.dao.company.CompanyInfoFeedbackDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class CompanyInfoFeedbackDaoImpl extends MybatisBaseDAO<CompanyInfoFeedback, String>implements CompanyInfoFeedbackDao{

	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.mapper.company.CompanyInfoFeedback";
	
	@Override
	public String getDefaultSqlNamespace() {
		return NAMESPACE_INFO;
	}

	@Override
	public boolean insertFeedback(CompanyInfoFeedback function) {
		boolean flag = false;
		try {
			this.insert("insertFeedback", function);
			return true;
		} catch (Exception e) {
			return flag;
		}
		
	}

	@Override
	public boolean insertFeedbackRelation(CompanyInfoFeedback function) {
		boolean flag = false;
		try {
			this.insert("insertFeedbackRelation", function);
			return true;
		} catch (Exception e) {
			return flag;
		}
	}

	@Override
	public List<CompanyInfoFeedback> getClassConfig() {
		return this.findListBy("getClassConfig");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyInfoFeedback> getClassTemplate(String configId){
		//return this.findListBy("getClassTemplate", function);
		return this.selectList("getClassTemplate", configId);
	}

}
