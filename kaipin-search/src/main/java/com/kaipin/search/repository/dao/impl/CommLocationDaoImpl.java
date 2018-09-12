package com.kaipin.search.repository.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.search.repository.dao.CommLocationDao;
import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class CommLocationDaoImpl  extends MybatisBaseDAO<Map, String>implements CommLocationDao{

	@Override
	public Map<String, Object> selectParentCode(String location_code) {
		 
 
		
		return  (Map<String, Object> )this.selectOne("selectParentCode", location_code);
	}

	@Override
	public Map<String, Object> selectGroupLoactionCode(String parent_code) {
 
		return  (Map<String, Object> )this.selectOne("selectGroupLoactionCode", parent_code);
	}

	@Override
	public String getDefaultSqlNamespace() {
		 
		return "CommLocationMapper";
	}

}
