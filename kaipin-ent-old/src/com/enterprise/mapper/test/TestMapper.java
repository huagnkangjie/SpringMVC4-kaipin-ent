package com.enterprise.mapper.test;

import java.util.List;
import java.util.Map;

public interface TestMapper {

	/**
	 * 获取用户表信息
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getList(Map<String,Object> map);
	
	void insertInfo(Map<String,Object> map);
	
	
	/**
	 * 数据迁移公共的方法
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getCommList(Map<String,Object> map);
	
	void insertComm(Map<String,Object> map);
}
