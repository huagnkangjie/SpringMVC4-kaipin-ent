package com.enterprise.service.resume;

import java.util.List;
import java.util.Map;

import com.enterprise.model.UserInterview;

public interface IUserInterviewService {
	int deleteByPrimaryKey(String id);

    int insertSelective(UserInterview record);

    UserInterview selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInterview record);

    /**
     * 获取简历日志
     * @param map
     * @return
     */
    public List<UserInterview> getLog(Map<String,Object> map);
    
    /**
     * 获取消息
     * @param map
     * @return
     */
    public List<Map<String,Object>> getMsg(Map<String,Object> map);
}
