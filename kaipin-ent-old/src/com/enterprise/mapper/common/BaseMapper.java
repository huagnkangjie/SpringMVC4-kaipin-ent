package com.enterprise.mapper.common;

import java.util.List;
import java.util.Map;

/**
 * 公用操作增删改
 * @author Mr-H
 *
 */

public interface BaseMapper {
	
	/**
	 * 公共插入数据
	 * 传入一个map遍历对用的key 和 value 进行插入数据
	 * @param map
	 * @return
	 */
	boolean insertVal(Map<String,Object> map);
	
	/**
	 * 公共的获取数据
	 * 传入参数，对应的表和参数
	 * 针对一个表，遍历map进行参数封装
	 * @param tableName
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getList(Map<String,Object> map);
	
	/**
	 * 获取String  Map
	 * @param map
	 * @return
	 */
	List<Map<String,String>> getListStr(Map<String,String> map);
	
	
}