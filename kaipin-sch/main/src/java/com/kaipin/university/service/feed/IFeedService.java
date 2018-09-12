package com.kaipin.university.service.feed;

import com.kaipin.university.model.feed.Feed;

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
	
	
	/**
	 * 逻辑删除，根据资源id,
	 * @param resourceId
	 * @return
	 */
	public int updateFeedByResourceId(String resourceId);
}
