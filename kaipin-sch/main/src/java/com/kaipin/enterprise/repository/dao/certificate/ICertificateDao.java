package com.kaipin.enterprise.repository.dao.certificate;

import java.util.List;
import java.util.Map;

public interface ICertificateDao {

	/**
	 * 获取企业资质文件
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getEntDoc(Map<String,Object> map);
}
