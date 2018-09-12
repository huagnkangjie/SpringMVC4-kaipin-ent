package com.kaipin.university.repository.dao.feed.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.repository.dao.feed.IFeedDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class FeedDaoImpl extends MybatisBaseDAO<Feed,String> implements IFeedDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.feed.IFeedDao";
	}
	
	@Override
	public int insertSelective(Feed record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public Feed selectByPrimaryKey(String id) {
		return (Feed) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(Feed record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int delFeedByResourceId(String resourceId) {
		return this.delete("delFeedByResourceId", resourceId);
	}
	
	@Override
	public int updateFeedByResourceId(String resourceId) {
		try {
			this.update("updateFeedByResourceId", resourceId);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
