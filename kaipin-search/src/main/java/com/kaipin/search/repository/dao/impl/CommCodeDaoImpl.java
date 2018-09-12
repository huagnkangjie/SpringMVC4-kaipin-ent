package com.kaipin.search.repository.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.search.repository.dao.ICommCodeDao;
import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class CommCodeDaoImpl extends MybatisBaseDAO<Object,String>  implements ICommCodeDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "CommCodeMapper";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCodeList(Map<String, Object> map) {
		return this.selectList("getCodeList", map);
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getBaseList(Map<String, Object> map) {
		return this.selectList("getBaseList", map);
	}
}
