package com.kaipin.search.repository.dao;

import java.util.List;
import java.util.Map;

public interface IPositionInfoDao {
	
	/**
	 * 获取企业职位统计
	 * @param companyId
	 * @return
	 */
	List<Map<String, Object>> getPositionCountByEntId(String companyId);
}
