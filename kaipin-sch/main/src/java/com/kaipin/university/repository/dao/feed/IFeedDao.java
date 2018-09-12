package com.kaipin.university.repository.dao.feed;

import com.kaipin.university.model.feed.Feed;

public interface IFeedDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(Feed record);

    Feed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Feed record);
    
    /**
	 * 删除动态，根据资源id
	 * @param resourceId
	 * @return
	 */
	int delFeedByResourceId(String resourceId);
	
	/**
	 * 逻辑删除，根据资源id,
	 * @param resourceId
	 * @return
	 */
	int updateFeedByResourceId(String resourceId);
}