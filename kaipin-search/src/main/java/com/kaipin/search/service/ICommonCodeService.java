package com.kaipin.search.service;

import java.util.List;
import java.util.Map;

public interface ICommonCodeService {

	/**
	 * 获取code表list
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getCodeList(Map<String,Object> map);
	
	/**
	 * 根据条件获取code集合 map
	 * @param map
	 * @return
	 */
	public Map<String,Object> getCodeMap(Map<String,Object> map);
	
	/**
	 * 获取码表名称
	 * 参数code
	 * @param map
	 * @return
	 */
	public String getNameByCode(String tableName, String columnName, String cvalue, String backColunm);
	
	/**
	 * 执行 sql 
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getBaseList(Map<String,Object> map);
	
	/**
	 * 根据code获取地区全称  如：四川 成都 武侯区
	 * @param code
	 * @return
	 */
	public String getAreaAllNameByCode(String code);
}
