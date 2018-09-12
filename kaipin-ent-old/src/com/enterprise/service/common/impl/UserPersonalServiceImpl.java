package com.enterprise.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.common.UserPersonalMapper;
import com.enterprise.model.common.UserPersonal;
import com.enterprise.service.common.IUserPersonalService;

@Service("userPersonalService")
@Repository
public class UserPersonalServiceImpl implements IUserPersonalService{

	@Autowired
	private UserPersonalMapper mapper;
	
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
	public int insertSelective(UserPersonal record) {
		try {
			mapper.insert(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public UserPersonal selectByPrimaryKey(String id) {
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int updateByPrimaryKeySelective(UserPersonal record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

}
