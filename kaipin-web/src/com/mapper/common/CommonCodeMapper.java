package com.mapper.common;

import java.util.List;
import java.util.Map;

public interface CommonCodeMapper {
	/**
	 * 获取code表list
	 * sql 
	 * @param map 
	 * @return
	 */
	List<Map<String,Object>> getCodeList(Map<String,Object> map);
	
}
