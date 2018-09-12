package com.enterprise.mapper.common;


import com.enterprise.model.common.MsgUserInterview;


public interface MsgUserInterviewMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgUserInterview record);

    int insertSelective(MsgUserInterview record);

    MsgUserInterview selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserInterview record);

    int updateByPrimaryKey(MsgUserInterview record);
    
}