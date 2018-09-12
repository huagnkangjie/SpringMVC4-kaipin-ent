package com.enterprise.service.common;

import java.util.List;
import java.util.Map;

import com.enterprise.model.common.MsgUserMetting;

public interface IMsgUserMettingService {

 	public int deleteByPrimaryKey(String id);

 	public int insertSelective(MsgUserMetting record);

 	public MsgUserMetting selectByPrimaryKey(String id);

 	public int updateByPrimaryKeySelective(MsgUserMetting record);
    
    /**
     * 某个企业发布的宣讲会提前30分钟通知
     * 
     * 查询伏符合时间段的宣讲会消息插入msg表中
     * @param map
     * @return
     */
 	public List<Map<String,Object>> msgMeetInsVal(Map<String,Object> map);
 	
 	/**
     * 批量插入消息
     * @param list
     * @return
     */
 	public int insertMsgList(List<MsgUserMetting> listVal);
    
    /**
     * WEB端获取宣讲会通知
     * @param map
     * @return
     */
 	public List<Map<String,Object>> msgMeetList(Map<String,Object> map);

}
