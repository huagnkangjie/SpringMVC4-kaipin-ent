package com.enterprise.service.position;

import com.enterprise.model.position.PositionInterview;

public interface IPInterviewService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(PositionInterview record);

	public PositionInterview selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(PositionInterview record);

}
