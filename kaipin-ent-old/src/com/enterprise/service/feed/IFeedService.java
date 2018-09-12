package com.enterprise.service.feed;

import com.enterprise.model.feed.Feed;

public interface IFeedService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(Feed record);

	public Feed selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(Feed record);
	
	/**
	 * 删除动态，根据资源id
	 * @param resourceId
	 * @return
	 */
	public int delFeedByResourceId(String resourceId);
}
