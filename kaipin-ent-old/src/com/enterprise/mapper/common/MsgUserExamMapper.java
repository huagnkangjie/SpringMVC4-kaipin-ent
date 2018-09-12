package com.enterprise.mapper.common;

import com.enterprise.model.common.MsgUserExam;

public interface MsgUserExamMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgUserExam record);

    int insertSelective(MsgUserExam record);

    MsgUserExam selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserExam record);

    int updateByPrimaryKey(MsgUserExam record);
}