package com.enterprise.service.common;

import java.util.List;
import java.util.Map;

/**
 * 公用码表接口
 * @author Mr-H
 *
 */

public interface IBaseCodeService {
	
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
	
	
	public void insertBase(Map<String,Object> map);
	

}
