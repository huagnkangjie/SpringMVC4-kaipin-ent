package com.kaipin.common.service.followfans.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.common.repository.dao.followfans.ICommFollowFansDao;
import com.kaipin.common.service.followfans.ICommFollowFansService;

@Service("commFollowFansService")
public class CommFollowFansServiceImpl implements ICommFollowFansService{

	@Autowired
	private ICommFollowFansDao dao;
	
	@Override
	public boolean insertFollowCount(String org_id) {
		return dao.insertFollowCount(org_id);
	}

	@Override
	public boolean updateFollowCount(Map<String, Object> map) {
		return dao.updateFollowCount(map);
	}
	
	@Override
	public List<Map<String, Object>> getFollowFanCounts(String org_id) {
		return dao.getFollowFanCounts(org_id);
	}

}
