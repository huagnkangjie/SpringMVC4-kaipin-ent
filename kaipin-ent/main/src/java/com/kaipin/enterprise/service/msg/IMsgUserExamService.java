package com.kaipin.enterprise.service.msg;

import com.kaipin.enterprise.model.msg.MsgUserExam;

public interface IMsgUserExamService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(MsgUserExam record);

    MsgUserExam selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(MsgUserExam record);

}
