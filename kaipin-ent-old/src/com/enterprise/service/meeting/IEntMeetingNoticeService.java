package com.enterprise.service.meeting;

import java.util.List;
import java.util.Map;

import com.enterprise.model.EntMeetingNotice;

public interface IEntMeetingNoticeService {
	public int deleteByPrimaryKey(String id);


	public int insertSelective(EntMeetingNotice record);

	public EntMeetingNotice selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(EntMeetingNotice record);

    
    /**
     * 获取list
     * @param map
     * @return
     */
	public List<EntMeetingNotice> getList(Map<String,Object> map);
	
	 /**
     * 获取宣讲会总数
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
