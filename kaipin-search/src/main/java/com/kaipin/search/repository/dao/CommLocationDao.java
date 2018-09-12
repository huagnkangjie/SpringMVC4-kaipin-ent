package com.kaipin.search.repository.dao;

import java.util.Map;

public interface CommLocationDao {
	
	public Map<String, Object> selectParentCode(String  location_code);
	
	
	public Map<String, Object> selectGroupLoactionCode(String  parent_code);
	
	
	
}
