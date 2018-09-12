package com.kaipin.university.repository.dao.feed;

import com.kaipin.university.model.feed.FeedComment;

public interface IFeedCommentDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(FeedComment record);

    FeedComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FeedComment record);
}