package com.kaipin.enterprise.service.msg;

import com.kaipin.enterprise.model.msg.MsgUserInterview;

public interface IMsgUserInterviewService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(MsgUserInterview record);

	public MsgUserInterview selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(MsgUserInterview record);

}
