package com.service.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mapper.common.CommonPushFollowMapper;
import com.service.common.ICommonPushFollowService;

@Service("commonPushFollowService")
@Repository
public class CommonPushFollowServiceImpl implements ICommonPushFollowService{

	@Autowired
	private CommonPushFollowMapper mapper;
	
	@Override
	public List<Map<String, Object>> getFollowList(Map<String,Object> map) {
		return mapper.getFollowList(map);
	}

	@Override
	public List<Map<String, Object>> checkIsFollow(Map<String,Object> map) {
		return mapper.checkIsFollow(map);
	}

	@Override
	public List<Map<String, Object>> getPushEnt(Map<String, Object> map) {
		return mapper.getPushEnt(map);
	}

	@Override
	public List<Map<String, Object>> getPushStu(Map<String, Object> map) {
		return mapper.getPushStu(map);
	}

	@Override
	public List<Map<String, Object>> getPushSch(Map<String, Object> map) {
		return mapper.getPushSch(map);
	}

	@Override
	public int addPushFoloow(Map<String, Object> map) {
		try {
			mapper.addPushFoloow(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delPushFoloow(Map<String, Object> map) {
		try {
			mapper.delPushFoloow(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean insertFollowCount(Map<String, Object> map) {
		try {
			mapper.insertFollowCount(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateFollowCount(Map<String, Object> map) {
		try {
			mapper.updateFollowCount(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
