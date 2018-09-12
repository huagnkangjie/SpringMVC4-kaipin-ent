package com.kaipin.university.service.feed.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.university.repository.dao.feed.IFeedBaseDao;
import com.kaipin.university.service.feed.IFeedBaseService;

@Service("feedBaseService")
public class FeedBaseServiceImpl implements IFeedBaseService{
	
	@Autowired
	private IFeedBaseDao dao;

	@Override
	public List<Map<String, Object>> getFeeds(Map<String, Object> map) {
		return dao.getFeeds(map);
	}

	@Override
	public int getFeedsCount(Map<String, Object> map) {
		return dao.getFeedsCount(map);
	}

	@Override
	public List<Map<String, Object>> getPosition(String id) {
		return dao.getPosition(id);
	}

	@Override
	public List<Map<String, Object>> getLiveInfo(String id) {
		return dao.getLiveInfo(id);
	}

	@Override
	public List<Map<String, Object>> getCompanyInfo(String id) {
		return dao.getCompanyInfo(id);
	}

	@Override
	public List<Map<String, Object>> getSchInfo(String id) {
		return dao.getSchInfo(id);
	}

	@Override
	public List<Map<String, Object>> getStuInfo(String id) {
		return dao.getStuInfo(id);
	}

	@Override
	public int getFeedZanCount(String id) {
		return dao.getFeedZanCount(id);
	}

	@Override
	public int checkFeedZan(Map<String, Object> map) {
		return dao.checkFeedZan(map);
	}

	@Override
	public int insertFeedZan(Map<String, Object> map) {
		return dao.insertFeedZan(map);
	}

	@Override
	public int delFeedZan(Map<String,Object> map) {
		return dao.delFeedZan(map);
	}

	@Override
	public List<Map<String, Object>> getCommentsLevelOne(String id) {
		return dao.getCommentsLevelOne(id);
	}

	@Override
	public List<Map<String, Object>> getCommentsLevelTwo(String id) {
		return dao.getCommentsLevelTwo(id);
	}

	@Override
	public List<Map<String, Object>> getFeedByOrgId(Map<String, Object> map) {
		return dao.getFeedByOrgId(map);
	}

	@Override
	public int getFeedByOrgIdCount(Map<String, Object> map) {
		return dao.getFeedByOrgIdCount(map);
	}

	@Override
	public int delFeedByResourceId(String resourceId) {
		return dao.delFeedByResourceId(resourceId);
	}

	@Override
	public int deleteFeedComment(Map<String,Object> map) {
		return dao.deleteFeedComment(map);
	}

	@Override
	public int deleteCommentById(String commentId) {
		return dao.deleteCommentById(commentId);
	}

	@Override
	public int deleteCommentByParentId(String commentId) {
		return dao.deleteCommentByParentId(commentId);
	}

	@Override
	public int getCommentLevelTowCount(String commentId) {
		return dao.getCommentLevelTowCount(commentId);
	}


}
