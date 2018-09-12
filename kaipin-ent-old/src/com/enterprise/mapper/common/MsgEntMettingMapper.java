package com.enterprise.mapper.common;

import java.util.List;
import java.util.Map;

import com.enterprise.model.common.MsgEntMetting;


public interface MsgEntMettingMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgEntMetting record);

    int insertSelective(MsgEntMetting record);

    MsgEntMetting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgEntMetting record);

    int updateByPrimaryKey(MsgEntMetting record);
    
    int insertEntMsgList(List<MsgEntMetting> list);
    
    /**
     * WEB端获取宣讲会通知
     * @param map
     * @return
     */
    List<Map<String,Object>> msgEntMeetList(Map<String,Object> map);
    
    
    /**
     * 获取面试总数
     * @param map
     * @return
     */
    List<Map<String,Object>> getMsgEntMeetCount(Map<String,Object> map);
}