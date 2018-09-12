package com.enterprise.mapper.resume;

import java.util.List;
import java.util.Map;

import com.enterprise.model.UserInterview;

public interface UserInterviewMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInterview record);

    int insertSelective(UserInterview record);

    UserInterview selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInterview record);

    int updateByPrimaryKey(UserInterview record);
    
    /**
     * 获取日志
     * @param map
     * @return
     */
    List<UserInterview> getLog(Map<String,Object> map);
    
    /**
     * 获取消息
     * @param map
     * @return
     */
    List<Map<String,Object>> getMsg(Map<String,Object> map);
    
 }