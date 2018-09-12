package com.kaipin.oss.service.company.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.model.company.CompanyDocument;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.repository.dao.company.CompanyInfoBaseDao;
import com.kaipin.oss.service.company.CompanyInfoBaseService;

@Service
public class CompanyInfoBaseServiceImpl implements CompanyInfoBaseService{

	
	@Autowired
	private CompanyInfoBaseDao dao;
	
	
	public IGenericPage<CompanyInfoCount> getPage(Map<String,Object> param, int pageNo,int pageSize){
		return dao.getPage(param,pageNo, pageSize);
	}


	@Override
	public IGenericPage<CompanyInfoCount> getCompanyDocList(Map<String,Object> param, int pageNo, int pageSize) {
		return dao.getCompanyDocList(param, pageNo, pageSize);
	}


	@Override
	public List<CompanyDocument> getComanyDoc(String companyId) {
		return dao.getComanyDoc(companyId);
	}


	@Override
	public List<Map<String, Object>> getRegeditCompanyCount(Map<String, Object> map) {
		return dao.getRegeditCompanyCount(map);
	}


	@Override
	public List<Map<String, Object>> getRegeditStudentCount(Map<String, Object> map) {
		return dao.getRegeditStudentCount(map);
	}


	@Override
	public List<Map<String, Object>> getRegeditAllCompanyCount() {
		return dao.getRegeditAllCompanyCount();
	}


	@Override
	public List<Map<String, Object>> getRegeditAllStudentCount() {
		return dao.getRegeditAllStudentCount();
	}


	@Override
	public List<Map<String, Object>> getPositionCount() {
		return dao.getPositionCount();
	}


	@Override
	public List<Map<String, Object>> getResumeCount() {
		return dao.getResumeCount();
	}


	@Override
	public List<Map<String, Object>> getPositionDeliveryCount() {
		return dao.getPositionDeliveryCount();
	}


	@Override
	public List<Map<String, Object>> getResumeDeliveryCount() {
		return dao.getResumeDeliveryCount();
	}


	@Override
	public List<Map<String, Object>> getXJHCount(String companyId) {
		return dao.getXJHCount(companyId);
	}


	@Override
	public List<Map<String, Object>> getOfferCount(String companyId) {
		return dao.getOfferCount(companyId);
	}


	@Override
	public List<Map<String, Object>> checkOfferConfig(String companyId) {
		return dao.checkOfferConfig(companyId);
	}


	@Override
	public List<Map<String, Object>> getRegeditSchCount(Map<String, Object> map) {
		return dao.getRegeditSchCount(map);
	}

}
