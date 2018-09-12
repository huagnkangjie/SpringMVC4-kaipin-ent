package com.enterprise.mapper.feed;

import java.util.Map;

import com.enterprise.model.feed.Feed;

public interface FeedMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Feed record);

    Feed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Feed record);
    
    /**
	 * 删除动态，根据资源id
	 * @param resourceId
	 * @return
	 */
	int delFeedByResourceId(Map<String, Object> map);
}