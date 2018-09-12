package com.kaipin.oss.service.position.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.position.PositionInfo;
import com.kaipin.oss.repository.dao.position.PositionInfoDao;
import com.kaipin.oss.service.position.PositionInfoService;

@Service
public class PositionInfoServiceImpl implements PositionInfoService{
	
	@Autowired
	private PositionInfoDao dao;

	@Override
	public IGenericPage<PositionInfo> getPositionList(Map<String, Object> param, int pageNo, int pageSize) {
		return dao.getPositionList(param, pageNo, pageSize);
	}

	@Override
	public PositionInfo selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

}
