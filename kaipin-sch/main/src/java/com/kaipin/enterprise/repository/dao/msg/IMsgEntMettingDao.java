package com.kaipin.enterprise.repository.dao.msg;

import java.util.List;
import java.util.Map;

import com.kaipin.enterprise.model.msg.MsgEntMetting;


public interface IMsgEntMettingDao {
	public int deleteByPrimaryKey(String id);


	public int insertSelective(MsgEntMetting record);

	public MsgEntMetting selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(MsgEntMetting record);
    
	public int insertEntMsgList(List<MsgEntMetting> list);

	/**
     * WEB端获取宣讲会通知
     * @param map
     * @return
     */
	public List<Map<String,Object>> msgEntMeetList(Map<String,Object> map);
	/**
     * 获取宣讲会总数
     * @param map
     * @return
     */
	public List<Map<String,Object>> getMsgEntMeetCount(Map<String,Object> map);
}
