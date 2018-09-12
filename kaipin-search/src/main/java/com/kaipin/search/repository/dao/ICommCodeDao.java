package com.kaipin.search.repository.dao;

import java.util.List;
import java.util.Map;

public interface ICommCodeDao {

	/**
	 * 获取code表list
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getCodeList(Map<String,Object> map);
	
	/**
	 * 根据条件获取code集合 map
	 * @param map
	 * @return
	 */
	Map<String,Object> getCodeMap(Map<String,Object> map);
	
	/**
	 * 获取码表名称
	 * 参数code
	 * @param map
	 * @return
	 */
	String getNameByCode(Map<String,Object> map);
	
	
	/**
	 * 执行 sql 
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getBaseList(Map<String,Object> map);
}
