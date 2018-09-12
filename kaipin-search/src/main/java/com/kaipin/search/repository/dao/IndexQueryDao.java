package com.kaipin.search.repository.dao;

import java.util.List;
import java.util.Map;

public interface IndexQueryDao {

	
	/**
	 * 推荐
	 * @return
	 */
	public List<Map<String,Object>> selectAppSearchRecommend(List<String> list);
	 
	/**
	 * app 搜索分页结果
	 * @param list
	 * @return
	 */
	public List<Map<String,Object>> 	 selectAppSearchResult(List<String> list);
	
	/**
	 * web  搜索分院结果
	 * @param list
	 * @return
	 */
	public List<Map<String,Object>> 	 selectWebSearchResult(List<String> list);
	
	/**
	 * 多个条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> 	 selectWebSearchResultByMap(Map<String, Object> map);
	
	
	
	
}
