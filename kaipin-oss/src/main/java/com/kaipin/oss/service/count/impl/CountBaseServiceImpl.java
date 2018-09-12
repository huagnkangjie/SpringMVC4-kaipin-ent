package com.kaipin.oss.service.count.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.count.CountBase;
import com.kaipin.oss.repository.dao.count.ICountBaseDao;
import com.kaipin.oss.service.count.ICountBaseService;

@Service("countBaseService")
public class CountBaseServiceImpl implements ICountBaseService{

	@Autowired
	private ICountBaseDao dao;
	
	@Override
	public IGenericPage<CountBase> getDeliveryPositionList(Map<String, Object> param, int pageNo, int pageSize) {
		return dao.getDeliveryPositionList(param, pageNo, pageSize);
	}

	@Override
	public List<Map<String, Object>> getWeekCount(Map<String, Object> map) {
		return dao.getWeekCount(map);
	}

	@Override
	public List<Map<String, Object>> getMonthCount(Map<String, Object> map) {
		return dao.getMonthCount(map);
	}

	@Override
	public List<Map<String, Object>> getQuarterCount(Map<String, Object> map) {
		return dao.getQuarterCount(map);
	}

	@Override
	public List<Map<String, Object>> getYearCount(Map<String, Object> map) {
		return dao.getYearCount(map);
	}

	@Override
	public List<Map<String, Object>> getTimeToTimeCount(Map<String, Object> map) {
		return dao.getTimeToTimeCount(map);
	}

	@Override
	public List<Map<String, Object>> getUserCount(Map<String, Object> map) {
		return dao.getUserCount(map);
	}

	@Override
	public List<Map<String, Object>> getMonthActiveCount(Map<String, Object> map) {
		return dao.getMonthActiveCount(map);
	}

}
