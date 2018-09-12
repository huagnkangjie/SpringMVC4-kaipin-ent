package com.kaipin.oss.service.sch.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.model.sch.SchInfoFeedback;
import com.kaipin.oss.repository.dao.sch.ISchInfoFeedbackDao;
import com.kaipin.oss.service.sch.ISchInfoFeedbackService;

@Service("schInfoFeedbackService")
public class SchInfoFeedbackServiceImpl implements ISchInfoFeedbackService{

	@Autowired
	private ISchInfoFeedbackDao dao;

	@Override
	public boolean insertFeedback(SchInfoFeedback function) {
		return dao.insertFeedback(function);
	}

	@Override
	public boolean insertFeedbackRelation(SchInfoFeedback function) {
		return dao.insertFeedbackRelation(function);
	}

	@Override
	public List<SchInfoFeedback> getClassConfig() {
		return dao.getClassConfig();
	}

	@Override
	public List<SchInfoFeedback> getClassTemplate(String configId) {
		return dao.getClassTemplate(configId);
	}
}
