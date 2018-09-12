package com.enterprise.service.common;

import com.enterprise.model.common.MsgUserExam;

public interface IMsgUserExamService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(MsgUserExam record);

    MsgUserExam selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(MsgUserExam record);

}
