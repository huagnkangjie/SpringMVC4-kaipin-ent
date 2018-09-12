package com.enterprise.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.common.BaseCodeMapper;
import com.enterprise.service.common.IBaseCodeService;

@Service("baseCodeService")
@Repository
public class BaseCodeServiceImpl implements IBaseCodeService{
	
	@Autowired
	private BaseCodeMapper mapper;

	@Override
	public List<Map<String, Object>> getCodeList(Map<String, Object> map) {
		return mapper.getCodeList(map);
	}

	@Override
	public Map<String, Object> getCodeMap(Map<String, Object> map) {
		return mapper.getCodeMap(map);
	}

	@Override
	public String getNameByCode(String tableName, String columnName, String cvalue, String backColunm) {
		try {
			String parameters = columnName + " " + " = '"+ cvalue +"'";
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("tableName", tableName);
			map.put("parameters", parameters);
			Object value = mapper.getCodeMap(map).get(backColunm);
			if(value == null){
				value = "未找到对应项";
			}
			return String.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}

	@Override
	public List<Map<String, Object>> getBaseList(Map<String, Object> map) {
		return mapper.getBaseList(map);
	}

	@Override
	public void insertBase(Map<String,Object> map) {
		mapper.insertBase(map);
	}


	
}
