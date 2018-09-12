package com.enterprise.service.feed.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.feed.FeedMapper;
import com.enterprise.model.feed.Feed;
import com.enterprise.service.feed.IFeedService;


@Service("feedService")
public class FeedServiceImpl implements IFeedService{
	@Autowired
	private FeedMapper dao;

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			dao.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(Feed record) {
		try {
			dao.insertSelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Feed selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Feed record) {
		try {
			dao.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int delFeedByResourceId(String resourceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resourceId", resourceId);
		return dao.delFeedByResourceId(map);
	}

	
}
