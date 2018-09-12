package com.kaipin.search.core.query;

import java.util.Map;

import org.apache.lucene.search.Query;

/**
 * 查询器构建
 * 
 *
 */
public interface QueryFactory {

	/**
	 * 职位查询
	 * 
	 * @param map
	 * @param boost
	 * @return
	 */
	public Query createPositionQuery(Map<String, Object> map, float boost);

	/**
	 * 公司查询
	 * 
	 * @param map
	 * @param boost
	 * @return
	 */
	public Query createCompanyQuery(Map<String, Object> map, float boost);

	/**
	 * 视频查询
	 * 
	 * @param map
	 * @param boost
	 * @return
	 */
	public Query createLiveQuery(Map<String, Object> map, float boost);
	
	/**
	 * 学生查询
	 * 
	 * @param map
	 * @param boost
	 * @return
	 */
	public Query createStuQuery(Map<String, Object> map, float boost);
	
	/**
	 * 学校查询
	 * 
	 * @param map
	 * @param boost
	 * @return
	 */
	public Query createSchQuery(Map<String, Object> map, float boost);

}
