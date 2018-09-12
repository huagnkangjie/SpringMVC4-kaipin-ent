package com.kaipin.student.repository.dao.resume;

import java.util.List;
import java.util.Map;

import com.kaipin.student.model.resume.UserInterview;


public interface IUserInterviewDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(UserInterview record);

    UserInterview selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInterview record);

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