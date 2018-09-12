package com.enterprise.service.resume.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.resume.SystemConfigMapper;
import com.enterprise.model.SystemConfig;
import com.enterprise.service.resume.ISystemConfigService;

@Service("systemConfigService")
@Repository
public class SystemConfigServiceImpl implements ISystemConfigService{

	@Autowired
	private SystemConfigMapper mapper;
	
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
	public int insertSelective(SystemConfig record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public SystemConfig selectByPrimaryKey(String id) {
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(SystemConfig record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String,Object>> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

}
