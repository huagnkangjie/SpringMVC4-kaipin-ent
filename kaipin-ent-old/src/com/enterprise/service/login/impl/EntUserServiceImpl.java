package com.enterprise.service.login.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.login.EntUserMapper;
import com.enterprise.model.common.EntUser;
import com.enterprise.service.login.IEntUserService;

@Service("entUserService")
@Repository
public class EntUserServiceImpl implements IEntUserService{
	
	@Autowired
	private EntUserMapper mapper;

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
	public int insertSelective(EntUser record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public EntUser selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EntUser record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public EntUser login(Map<String, Object> map) {
		return mapper.login(map);
	}

	@Override
	public List<Map<String, Object>> getUserInfoAndConfig(
			Map<String, Object> map) {
		return mapper.getUserInfoAndConfig(map);
	}

}
