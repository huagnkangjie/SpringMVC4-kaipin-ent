package com.kaipin.student.service.resume;

import java.util.List;
import java.util.Map;

import com.kaipin.student.model.resume.UserInterview;

public interface IUserInterviewService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(UserInterview record);

    public UserInterview selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(UserInterview record);

    /**
     * 获取日志
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
