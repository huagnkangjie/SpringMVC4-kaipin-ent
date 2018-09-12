package com.kaipin.enterprise.repository.dao.msg;

import com.kaipin.enterprise.model.msg.MsgUserInterview;

public interface IMsgUserInterviewDao {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(MsgUserInterview record);

	public MsgUserInterview selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(MsgUserInterview record);
}
