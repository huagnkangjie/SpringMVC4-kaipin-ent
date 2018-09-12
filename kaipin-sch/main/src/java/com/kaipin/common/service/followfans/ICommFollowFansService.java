package com.kaipin.common.service.followfans;

import java.util.List;
import java.util.Map;

public interface ICommFollowFansService {

	/**
	 * 添加关注和粉丝表的统计数
	 * @return
	 */
	public boolean insertFollowCount(String org_id);
	
	/**
	 * 根据统计表获取关注数和粉丝数
	 * @param org_id
	 * @return
	 */
	public List<Map<String, Object>> getFollowFanCounts(String org_id);
	
	/**
	 * 更新关注和粉丝表的统计数
	 * @param org_id
	 * @return
	 */
	public boolean updateFollowCount(Map<String, Object> map);
	
}
