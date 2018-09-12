package com.enterprise.service.resume;

import java.util.Map;

import com.enterprise.model.UserResumeRelation;

public interface IUserResumeRelationService {

	/**
	 * 根据id 主键
	 * 更新数据状态
	 * @param id
	 * @return
	 */
	public int updateStatus(Map<String,String> map);
	
	public UserResumeRelation getuserResumeRelation(String id);
	
	public int updateByPrimaryKeySelective(UserResumeRelation record);
	
}
