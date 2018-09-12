package com.enterprise.mapper.common;

import java.util.List;
import java.util.Map;

import com.enterprise.model.common.MsgEntInterview;


public interface MsgEntInterviewMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgEntInterview record);

    int insertSelective(MsgEntInterview record);

    MsgEntInterview selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgEntInterview record);

    int updateByPrimaryKey(MsgEntInterview record);
    
    /**
     * 获取企业的所有面试通知
     * @param map
     * @return
     */
    List<Map<String,Object>> getMsgEntViewList(Map<String,Object> map);
    
    /**
     * 获取宣讲会总数
     * @param map
     * @return
     */
    List<Map<String,Object>> getMsgEntViewCount(Map<String,Object> map);
    
    /**
     * 批量插入数据
     * @param list
     * @return
     */
    int  insertList(List<MsgEntInterview> list);
    
    /**
     * 获取msg对象
     * @param id
     * @return
     */
    MsgEntInterview getMsgEntViewById(String id);
    
    /**
     * 修改阅读状态
     * @param id
     * @return
     */
    int updateStatusById(String id);
}