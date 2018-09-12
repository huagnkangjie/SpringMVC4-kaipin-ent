package com.enterprise.service.common;

import com.enterprise.model.common.MsgUserInterview;

public interface IMsgUserInterviewService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(MsgUserInterview record);

	public MsgUserInterview selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(MsgUserInterview record);

}
