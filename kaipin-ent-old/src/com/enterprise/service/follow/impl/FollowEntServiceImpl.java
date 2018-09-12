package com.enterprise.service.follow.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.follow.FollowEntMapper;
import com.enterprise.model.FollowEnt;
import com.enterprise.service.follow.IFollowEntService;

@Service("followEntService")
@Repository
public class FollowEntServiceImpl implements IFollowEntService{
	
	@Autowired
	private FollowEntMapper mapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(FollowEnt record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public FollowEnt selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FollowEnt record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> getFollowCount(Map<String, Object> map) {
		return mapper.getFollowCount(map);
	}

	@Override
	public List<Map<String, Object>> getFollowCountList(Map<String, Object> map) {
		return mapper.getFollowCountList(map);
	}

}
