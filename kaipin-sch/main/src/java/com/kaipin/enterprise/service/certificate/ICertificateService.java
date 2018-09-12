package com.kaipin.enterprise.service.certificate;

import java.util.List;
import java.util.Map;

public interface ICertificateService {

	/**
	 * 获取企业资质文件
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getEntDoc(Map<String,Object> map);
}
