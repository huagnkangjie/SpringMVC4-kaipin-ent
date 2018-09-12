package com.kaipin.university.repository.dao.feed.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.repository.dao.feed.IFeedBaseDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class FeedBaseDaoImpl extends MybatisBaseDAO<Feed,String> implements IFeedBaseDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.feed.IFeedBaseDao";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getFeeds(Map<String, Object> map) {
		return this.selectList("getFeeds", map);
	}

	@Override
	public int getFeedsCount(Map<String, Object> map) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> list = this.selectList("getFeedsCount", map);
			int count = 0;
			if(list.size() > 0){
				count = Integer.valueOf(list.get(0).get("counts")+"");
			}
			return count;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getPosition(String id) {
		return this.selectList("getPosition", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getLiveInfo(String id) {
		return this.selectList("getLiveInfo", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCompanyInfo(String id) {
		return this.selectList("getCompanyInfo", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getSchInfo(String id) {
		return this.selectList("getSchInfo", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getStuInfo(String id) {
		return this.selectList("getStuInfo", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getFeedZanCount(String id) {
		try {
			int count = 0;
			List<Map<String,Object>> list = this.selectList("getFeedZanCount", id);
			if(list.size() > 0){
				count = Integer.valueOf(list.get(0).get("counts")+"");
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int checkFeedZan(Map<String, Object> map) {
		try {
			int count = 0;
			List<Map<String,Object>> list = this.selectList("checkFeedZan", map);
			if(list.size() > 0){
				count = Integer.valueOf(list.get(0).get("counts")+"");
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertFeedZan(Map<String, Object> map) {
		try {
			this.insert("insertFeedZan", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delFeedZan(Map<String,Object> map) {
		try {
			this.delete("delFeedZan", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCommentsLevelOne(String id) {
		return this.selectList("getCommentsLevelOne", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCommentsLevelTwo(String id) {
		return this.selectList("getCommentsLevelTwo", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getFeedByOrgId(Map<String, Object> map) {
		return this.selectList("getFeedByOrgId", map);
	}

	@Override
	public int getFeedByOrgIdCount(Map<String, Object> map) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> list = this.selectList("getFeedByOrgIdCount", map);
			int count = 0;
			if(list.size() > 0){
				count = Integer.valueOf(list.get(0).get("counts")+"");
			}
			return count;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int delFeedByResourceId(String resourceId) {
		return this.update("delFeedByResourceId", resourceId);
	}

	@Override
	public int deleteFeedComment(Map<String,Object> map) {
		try {
			this.update("deleteFeedComment", map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int deleteCommentById(String commentId) {
		try {
			this.update("deleteCommentById", commentId);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int deleteCommentByParentId(String commentId) {
		try {
			this.update("deleteCommentByParentId", commentId);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCommentLevelTowCount(String commentId) {
		try {
			List<Map<String, Object>> list = this.selectList("getCommentLevelTowCount", commentId);
			int count = 0;
			if(list.size() > 0){
				count = Integer.valueOf(list.get(0).get("counts")+"");
			}
			
			return count;
		} catch (Exception e) {
			return 0;
		}
	}



}
