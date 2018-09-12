package com.kaipin.search.repository.dao;

import java.util.List;
import java.util.Map;

import com.kaipin.search.common.page.IGenericPage;
import com.kaipin.search.entity.SearchLuceneTasks;

/**
 * 搜索增量数据管理
 *  
 *
 */
public interface SearchLuceneTasksDao {
	
	int insertLuceneTask(Map<String, Object> map);
	
    int deleteByPrimaryKey(String id);

 
    SearchLuceneTasks selectByPrimaryKey(String id);
    
    
    /**
     * 更新已处理
     * @param record
     * @return
     */
    int updateHandled(String id);
    
    
    int  updateBatchHandle(List<String> list);
    
    /**
     *查询未处理数据
     * @return
     */
    IGenericPage<SearchLuceneTasks> selectUnHandle(int pageNo, int pageSize);
    

    int updateByPrimaryKeySelective(SearchLuceneTasks record);

 
}