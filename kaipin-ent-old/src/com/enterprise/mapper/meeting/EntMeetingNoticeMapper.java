package com.enterprise.mapper.meeting;

import java.util.List;
import java.util.Map;

import com.enterprise.model.EntMeetingNotice;

public interface EntMeetingNoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(EntMeetingNotice record);

    int insertSelective(EntMeetingNotice record);

    EntMeetingNotice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntMeetingNotice record);

    int updateByPrimaryKey(EntMeetingNotice record);
    
    /**
     * 获取list
     * @param map
     * @return
     */
    List<EntMeetingNotice> getList(Map<String,Object> map);
    
    
    /**
     * 获取
     * @param map
     * @return
     */
    List<Map<String,Object>> getListCounts(Map<String,Object> map);
    
    /**
     * 获取宣讲会和附件信息
     * @return
     */
    Map<String,Object> getMap(Map<String,Object> map);
    
}