package com.enterprise.service.login.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.login.EntBaseinfoMapper;
import com.enterprise.model.EntBaseinfo;
import com.enterprise.service.login.IEntBaseinfoService;



@Service("entBaseinfoService")
@Repository
public class EntBaseinfoServiceImpl implements IEntBaseinfoService{
	
	@Autowired
	private EntBaseinfoMapper entBaseinfoMapper;

	@Override
	public EntBaseinfo login(Map<String, Object> map) {
		return entBaseinfoMapper.login(map);
	}

	@Override
	public int insertSelective(EntBaseinfo record) {
		try {
			entBaseinfoMapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> emailValidata(Map<String, Object> map) {
		return entBaseinfoMapper.emailValidata(map);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			entBaseinfoMapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int updateByPrimaryKeySelective(EntBaseinfo record) {
		entBaseinfoMapper.updateByPrimaryKeySelective(record);
		try {
			entBaseinfoMapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public EntBaseinfo selectByPrimaryKey(String id) {
		return entBaseinfoMapper.selectByPrimaryKey(id);
	}

}
