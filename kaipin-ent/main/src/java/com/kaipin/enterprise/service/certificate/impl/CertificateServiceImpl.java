package com.kaipin.enterprise.service.certificate.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.repository.dao.certificate.ICertificateDao;
import com.kaipin.enterprise.service.certificate.ICertificateService;

@Service("certificateService")
public class CertificateServiceImpl implements ICertificateService{

	@Autowired
	private ICertificateDao dao;
	
	@Override
	public List<Map<String, Object>> getEntDoc(Map<String, Object> map) {
		return dao.getEntDoc(map);
	}

}
