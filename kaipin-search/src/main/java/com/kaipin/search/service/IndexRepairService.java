package com.kaipin.search.service;

import java.util.Map;

/**
 * 索引维护
 
 */
public interface IndexRepairService {

	/**
	 * 创建索引
	 * @return
	 */
	public boolean createIndex(Map<String,Object> map);
	/**
	 * 更新索引
	 * @return
	 */
	public boolean updateIndex(Map<String,Object> map);

	/**
	 * 删除
	 * @param map
	 * @return
	 */
	public boolean deleteIndex(String id);
}
