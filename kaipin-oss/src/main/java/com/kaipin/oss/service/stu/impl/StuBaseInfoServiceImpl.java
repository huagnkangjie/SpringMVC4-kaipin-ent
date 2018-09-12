package com.kaipin.oss.service.stu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.stu.StuBaseInfo;
import com.kaipin.oss.repository.dao.stu.IStuBaseInfoDao;
import com.kaipin.oss.service.stu.IStuBaseInfoService;

@Service("stuBaseInfoService")
public class StuBaseInfoServiceImpl implements IStuBaseInfoService{
	
	@Autowired
	private IStuBaseInfoDao dao;

	@Override
	public IGenericPage<StuBaseInfo> getStuList(Map<String, Object> param, int pageNo, int pageSize) {
		return dao.getStuList(param, pageNo, pageSize);
	}

	@Override
	public IGenericPage<StuBaseInfo> getStuVipList(Map<String, Object> param, int pageNo, int pageSize) {
		return dao.getStuVipList(param, pageNo, pageSize);
	}

	@Override
	public List<Map<String, Object>> getLikePositionList(Map<String, Object> param) {
		return dao.getLikePositionList(param);
	}

	@Override
	public long getOneToOneCount(String uid) {
		return dao.getOneToOneCount(uid);
	}

	@Override
	public long getOneToOnePositionCount(String uid) {
		return dao.getOneToOnePositionCount(uid);
	}

	@Override
	public boolean insertVipRecodGroup(Map<String, Object> map) {
		return dao.insertVipRecodGroup(map);
	}

	@Override
	public boolean insertVipRecod(Map<String, Object> map) {
		return dao.insertVipRecod(map);
	}

}
