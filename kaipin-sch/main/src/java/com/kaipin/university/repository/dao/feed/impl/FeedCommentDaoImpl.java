package com.kaipin.university.repository.dao.feed.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.model.feed.FeedComment;
import com.kaipin.university.repository.dao.feed.IFeedCommentDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class FeedCommentDaoImpl extends MybatisBaseDAO<Feed,String> implements IFeedCommentDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.feed.IFeedCommentDao";
	}
	
	@Override
	public int insertSelective(FeedComment record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public FeedComment selectByPrimaryKey(String id) {
		return (FeedComment) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(FeedComment record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

}
