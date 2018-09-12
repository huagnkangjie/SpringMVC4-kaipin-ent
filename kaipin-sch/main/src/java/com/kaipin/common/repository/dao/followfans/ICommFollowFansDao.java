package com.kaipin.common.repository.dao.followfans;

import java.util.List;
import java.util.Map;

public interface ICommFollowFansDao {

	/**
	 * 添加关注和粉丝表的统计数
	 * @return
	 */
	boolean insertFollowCount(String org_id);
	
	/**
	 * 根据统计表获取关注数和粉丝数
	 * @param org_id
	 * @return
	 */
	List<Map<String, Object>> getFollowFanCounts(String org_id);
	
	/**
	 * 更新关注和粉丝表的统计数
	 * @param org_id
	 * @return
	 */
	boolean updateFollowCount(Map<String, Object> map);
}
