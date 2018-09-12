package com.kaipin.common.repository.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.feed.Feed;
import com.kaipin.common.repository.dao.common.IBaseCodeDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;

@Repository
@SuppressWarnings("unchecked")
public class BaseCodeDaoImpl extends MybatisBaseDAO<Feed,String> implements IBaseCodeDao{
	
	
	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.common.repository.dao.common.IBaseCodeDao";
	}

	@Override
	public List<Map<String, Object>> getCodeList(Map<String, Object> map) {
		return this.selectList("getCodeList", map);
	}

	@Override
	public Map<String, Object> getCodeMap(Map<String, Object> map) {
		List<Map<String, Object>> list = this.selectList("getCodeMap", map);
		Map<String, Object> maps = new HashMap<String,Object>();
		if(list.size() > 0){
			maps = list.get(0);
		}
		return maps;
	}

	@Override
	public String getNameByCode(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Map<String, Object>> getBaseList(Map<String, Object> map) {
		return this.selectList("getBaseList", map);
	}

	@Override
	public List<Map<String, Object>> getLocationListContainOwn(String locationCode) {
		return this.selectList("getLocationListContainOwn", locationCode);
	}

	@Override
	public List<Map<String, Object>> getLocationListExceptOwn(String locationCode) {
		return this.selectList("getLocationListExceptOwn", locationCode);
	}

	

}
