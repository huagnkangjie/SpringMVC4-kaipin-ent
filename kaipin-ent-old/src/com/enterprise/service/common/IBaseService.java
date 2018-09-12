package com.enterprise.service.common;

import java.util.List;
import java.util.Map;

/**
 * 公用增删改接口
 * @author Mr-H
 *
 */

public interface IBaseService {
	/**
	 * 公共插入数据
	 * 传入一个map遍历对用的key 和 value 进行插入数据
	 * @param map
	 * @return
	 */
	public boolean insert(String tableName, Map<String,Object> map);
	
	/**
	 * 公共的获取数据
	 * 传入参数，对应的表和参数
	 * 针对一个表，遍历map进行参数封装
	 * @param tableName
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getList(String tableName, Map<String,Object> map, String orderCol);
	
	/**
	 * 获取String Map
	 * @param map key=sql value=拼接好的sql
	 * @return
	 */
	public List<Map<String,String>> getListStr(Map<String,String> map);

}
