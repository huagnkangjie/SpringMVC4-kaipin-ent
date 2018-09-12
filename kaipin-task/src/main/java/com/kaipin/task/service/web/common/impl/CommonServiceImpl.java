package com.kaipin.task.service.web.common.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.task.repository.dao.web.common.ICommonDao;
import com.kaipin.task.service.web.common.ICommonService;

@Service("commonService")
public class CommonServiceImpl implements ICommonService{
	
	@Autowired
	private ICommonDao dao;
	
	@Override
	public Map<String, Object> getCompanyInfo(String companyId) {
		return dao.getCompanyInfo(companyId);
	}

	@Override
	public Map<String, Object> getSchoolInfo(String schId) {
		return dao.getSchoolInfo(schId);
	}

	@Override
	public Map<String, Object> getStudentInfo(String stuId) {
		return dao.getStudentInfo(stuId);
	}

}
