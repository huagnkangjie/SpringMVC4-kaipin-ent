package com.enterprise.service.follow;

import java.util.List;
import java.util.Map;

import com.enterprise.model.FollowEnt;

public interface IFollowEntService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(FollowEnt record);

	public FollowEnt selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(FollowEnt record);
	
	/**
	 * 获取企业关注数
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getFollowCount(Map<String,Object> map);
	
	/**
     * 获取企业最新关注列表
     * @param map
     * @return
     */
    List<Map<String,Object>> getFollowCountList(Map<String,Object> map);

}
