package com.kaipin.search.repository.dao;

import java.util.List;
import java.util.Map;

import com.kaipin.search.common.page.IGenericPage;

/**
 * 
 *
 * 
 */
public interface IndexBuildDao  extends IndexQueryDao{

	public IGenericPage getSearchAll(Map<String, Object> param, int pageNo, int pageSize);

	public Map<String, Object> selectByPrimaryKey(String id);
	
	

}
