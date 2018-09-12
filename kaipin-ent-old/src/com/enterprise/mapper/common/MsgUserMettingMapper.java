package com.enterprise.mapper.common;

import java.util.List;
import java.util.Map;

import com.enterprise.model.common.MsgUserMetting;


public interface MsgUserMettingMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgUserMetting record);

    int insertSelective(MsgUserMetting record);

    MsgUserMetting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserMetting record);

    int updateByPrimaryKey(MsgUserMetting record);
    
    /**
     * 某个企业发布的宣讲会提前30分钟通知
     * 
     * 查询伏符合时间段的宣讲会消息插入msg表中
     * @param map
     * @return
     */
    List<Map<String,Object>> msgMeetInsVal(Map<String,Object> map);
    
    /**
     * 批量插入消息
     * @param list
     * @return
     */
    int insertMsgList(List<MsgUserMetting> listVal);
    
    /**
     * WEB端获取宣讲会通知
     * @param map
     * @return
     */
    List<Map<String,Object>> msgMeetList(Map<String,Object> map);
}