package com.kaipin.university.service.feed.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.university.model.feed.FeedComment;
import com.kaipin.university.repository.dao.feed.IFeedCommentDao;
import com.kaipin.university.service.feed.IFeedCommentService;

@Service("feedCommentService")
public class FeedCommentServiceImpl implements IFeedCommentService{
	
	@Autowired
	private IFeedCommentDao dao;

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
	public int insertSelective(FeedComment record) {
		try {
			dao.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public FeedComment selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FeedComment record) {
		try {
			dao.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
