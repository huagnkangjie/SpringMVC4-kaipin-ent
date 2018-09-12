package com.enterprise.service.resume.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.resume.UserInterviewMapper;
import com.enterprise.model.UserInterview;
import com.enterprise.service.resume.IUserInterviewService;

@Service("userInterviewService")
@Repository
public class UserInterviewServiceImpl implements IUserInterviewService{

	@Autowired
	private UserInterviewMapper mapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(UserInterview record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public UserInterview selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInterview record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<UserInterview> getLog(Map<String, Object> map) {
		return mapper.getLog(map);
	}

	@Override
	public List<Map<String, Object>> getMsg(Map<String, Object> map) {
		return mapper.getMsg(map);
	}

}
