package com.kaipin.university.service.feed;

import com.kaipin.university.model.feed.FeedComment;

public interface IFeedCommentService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(FeedComment record);

	public FeedComment selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(FeedComment record);
}
