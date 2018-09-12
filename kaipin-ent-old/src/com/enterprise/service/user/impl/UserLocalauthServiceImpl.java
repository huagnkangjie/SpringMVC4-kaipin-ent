package com.enterprise.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.user.UserLocalauthMapper;
import com.enterprise.model.user.UserLocalauth;
import com.enterprise.service.user.IUserLocalauthService;


@Service("userLocalauthService")
@Repository
public class UserLocalauthServiceImpl implements IUserLocalauthService{

	@Autowired
	private UserLocalauthMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(UserLocalauth record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public UserLocalauth selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLocalauth record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
