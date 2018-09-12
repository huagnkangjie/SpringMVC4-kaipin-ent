package com.kaipin.university.service.feed.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.repository.dao.feed.IFeedDao;
import com.kaipin.university.service.feed.IFeedService;

@Service("feedService")
public class FeedServiceImpl implements IFeedService{
	@Autowired
	private IFeedDao dao;

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
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delFeedByResourceId(String resourceId) {
		return dao.delFeedByResourceId(resourceId);
	}

	@Override
	public int updateFeedByResourceId(String resourceId) {
		return dao.updateFeedByResourceId(resourceId);
	}
}
