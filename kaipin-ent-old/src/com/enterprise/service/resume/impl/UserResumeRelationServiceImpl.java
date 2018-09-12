package com.enterprise.service.resume.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.resume.UserResumeRelationMapper;
import com.enterprise.model.UserResumeRelation;
import com.enterprise.service.resume.IUserResumeRelationService;

@Service("userResumeRelationService")
@Repository
public class UserResumeRelationServiceImpl implements IUserResumeRelationService{

	@Autowired
	private UserResumeRelationMapper mapper;
	
	@Override
	public int updateStatus(Map<String,String> map) {
		try {
			mapper.updateStatus(map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
		
	}

	@Override
	public UserResumeRelation getuserResumeRelation(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserResumeRelation record) {
		return mapper.updateByPrimaryKeySelective(record);
	}


}
