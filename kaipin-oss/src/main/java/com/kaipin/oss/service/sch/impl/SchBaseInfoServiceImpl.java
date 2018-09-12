package com.kaipin.oss.service.sch.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.sch.SchBaseInfo;
import com.kaipin.oss.repository.dao.sch.ISchBaseInfoDao;
import com.kaipin.oss.service.sch.ISchBaseInfoService;

@Service("schBaseInfoService")
public class SchBaseInfoServiceImpl implements ISchBaseInfoService{
	
	@Autowired
	private ISchBaseInfoDao dao;

	@Override
	public IGenericPage<SchBaseInfo> getSchDocList(Map<String, Object> param, int pageNo, int pageSize) {
		return dao.getSchDocList(param, pageNo, pageSize);
	}

	@Override
	public List<Map<String, Object>> getSchDocList(String schUserId) {
		return dao.getSchDocList(schUserId);
	}

}
