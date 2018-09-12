package com.kaipin.oss.service.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.model.company.CompanyInfoFeedback;
import com.kaipin.oss.repository.dao.company.CompanyInfoFeedbackDao;
import com.kaipin.oss.service.company.CompanyInfoFeedbackService;

@Service
public class CompanyInfoFeedbackServiceImpl implements CompanyInfoFeedbackService {
	
	@Autowired
	private CompanyInfoFeedbackDao dao;

	@Override
	public boolean insertFeedback(CompanyInfoFeedback function) {
		return dao.insertFeedback(function);
	}

	@Override
	public boolean insertFeedbackRelation(CompanyInfoFeedback function) {
		return dao.insertFeedbackRelation(function);
	}

	@Override
	public List<CompanyInfoFeedback> getClassConfig() {
		return dao.getClassConfig();
	}

	@Override
	public List<CompanyInfoFeedback> getClassTemplate(String configId) {
		return dao.getClassTemplate(configId);
	}

}
