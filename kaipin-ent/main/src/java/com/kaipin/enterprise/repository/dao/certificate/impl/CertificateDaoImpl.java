package com.kaipin.enterprise.repository.dao.certificate.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.repository.dao.certificate.ICertificateDao;

@Repository
@SuppressWarnings("unchecked")
public class CertificateDaoImpl extends MybatisBaseDAO<Object, String> implements ICertificateDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.certificate.ICertificateDao";
	}

	
	@Override
	public List<Map<String, Object>> getEntDoc(Map<String, Object> map) {
		try {
			return this.selectList("getEntDoc", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
