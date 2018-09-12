package com.kaipin.search.service;

import java.util.List;
import java.util.Map;

public interface IPositionInfoService {
	/**
	 * 获取企业职位统计
	 * @param companyId
	 * @return
	 */
	String getPositionCountByEntId(String companyId);
}
