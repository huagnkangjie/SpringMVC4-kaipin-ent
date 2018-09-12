package com.kaipin.enterprise.service.msg;

import java.util.List;
import java.util.Map;

import com.kaipin.enterprise.model.msg.MsgEntInterview;


public interface IMsgEntInterviewService {
	
	public int deleteByPrimaryKey(String id);


	public int insertSelective(MsgEntInterview record);

	public MsgEntInterview selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(MsgEntInterview record);

    /**
     * 获取企业的所有面试通知
     * @param map
     * @return
     */
	public List<Map<String,Object>> getMsgEntViewList(Map<String,Object> map);
	/**
     * 获取面试总数
     * @param map
     * @return
     */
	public List<Map<String,Object>> getMsgEntViewtCount(Map<String,Object> map);
    
    /**
     * 批量插入数据
     * @param list
     * @return
     */
	public int  insertList(List<MsgEntInterview> list);
	
	/**
     * 获取msg对象
     * @param id
     * @return
     */
	public MsgEntInterview getMsgEntViewById(String id);
    
    /**
     * 修改阅读状态
     * @param id
     * @return
     */
	public int updateStatusById(String id);

}
