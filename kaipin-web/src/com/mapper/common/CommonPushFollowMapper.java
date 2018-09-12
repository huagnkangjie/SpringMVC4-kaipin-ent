package com.mapper.common;

import java.util.List;
import java.util.Map;

public interface CommonPushFollowMapper {

	/**
	 * 获取用户的关注列表
	 * @param uId 被关注的用户
	 * @return
	 */
	List<Map<String,Object>> getFollowList(Map<String,Object> map);
	
	/**
	 * 判断某个用户是否关注了某用户
	 * @param fromUid 用户
	 * @param toUid 被关注的用户
	 * @return
	 */
	List<Map<String,Object>> checkIsFollow(Map<String,Object> map);
	
	/**
	 * 添加关注
	 * @param map
	 * @return
	 */
	int addPushFoloow(Map<String,Object> map);
	
	/**
	 * 删除关注
	 * @param map
	 * @return
	 */
	int delPushFoloow(Map<String,Object> map);
	
	/**
	 * 添加关注和粉丝表的统计数
	 * @return
	 */
	boolean insertFollowCount(Map<String, Object> map);
	
	/**
	 * 更新关注和粉丝表的统计数
	 * @param org_id
	 * @return
	 */
	boolean updateFollowCount(Map<String, Object> map);
	
	/**
	 * 获取推荐的企业
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getPushEnt(Map<String,Object> map);
	
	/**
	 * 获取推荐的学生
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getPushStu(Map<String,Object> map);
	
	/**
	 * 获取推荐的学校
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getPushSch(Map<String,Object> map);
	
}
