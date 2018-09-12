package com.enterprise.mapper.follow;

import java.util.List;
import java.util.Map;

import com.enterprise.model.FollowEnt;

public interface FollowEntMapper {
    int deleteByPrimaryKey(String id);

    int insert(FollowEnt record);

    int insertSelective(FollowEnt record);

    FollowEnt selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FollowEnt record);

    int updateByPrimaryKey(FollowEnt record);
    
    /**
     * 获取企业关注数
     * @param map
     * @return
     */
    List<Map<String,Object>> getFollowCount(Map<String,Object> map);
    
    /**
     * 获取企业最新关注列表
     * @param map
     * @return
     */
    List<Map<String,Object>> getFollowCountList(Map<String,Object> map);
}