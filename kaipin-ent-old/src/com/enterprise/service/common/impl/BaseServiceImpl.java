package com.enterprise.service.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.common.BaseMapper;
import com.enterprise.service.common.IBaseService;
import com.util.StringUtil;

@Service("baseService")
@Repository
public class BaseServiceImpl implements IBaseService{
	
	@Autowired
	private BaseMapper mapper;

	@Override
	public boolean insert(String tableName,Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		StringBuffer val = new StringBuffer();
		String param[] = new String[map.keySet().size()];
		map.keySet().toArray(param);
		for(int i = 0;i<param.length;i++){
			String key = param[i];
			String value = (String)map.get(key);
			if(StringUtil.isNotEmpty(value)){
				val.append("'"+ value +"',");
			}
		}
		sql.append(" insert into " + tableName + " values("+val.toString().substring(0, 
				val.toString().length() - 1)+")");
		map.clear();
		map.put("sql", sql.toString());
		return mapper.insertVal(map);
	}

	@Override
	public List<Map<String, Object>> getList(String tableName, Map<String, Object> map, String orderCol) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from " + tableName + " where 1=1 ");
		String param[] = new String[map.keySet().size()];
		map.keySet().toArray(param);
		for(int i = 0;i<param.length;i++){
			String key = param[i];
			String value = (String)map.get(key);
			if(StringUtil.isNotEmpty(value)){
				sql.append(" and "+key+" = '"+value+"' ");
			}
		}
		if(StringUtil.isNotEmpty(orderCol)){
			sql.append(" order by " + orderCol + " desc" );
		}
		map.clear();
		map.put("sql", sql.toString());
		return mapper.getList(map);
	}

	@Override
	public List<Map<String, String>> getListStr(Map<String, String> map) {
		return mapper.getListStr(map);
	}
	
}
