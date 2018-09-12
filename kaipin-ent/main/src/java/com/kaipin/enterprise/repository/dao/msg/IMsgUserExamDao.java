package com.kaipin.enterprise.repository.dao.msg;

import com.kaipin.enterprise.model.msg.MsgUserExam;

public interface IMsgUserExamDao {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(MsgUserExam record);

    MsgUserExam selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(MsgUserExam record);

}
