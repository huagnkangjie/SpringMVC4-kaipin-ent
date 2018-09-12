package com.kaipin.search.manager.task;

import com.kaipin.search.entity.SearchLuceneTasks;

public interface TaskHandler {

    
    
    /**
     * 检查是否是当前处理服务
     * @param objType
     * @return
     */
    public boolean objType(byte objType);
    
    /**
     * 索引的建立
     * @param bean
     * @return
     */
    public boolean incrementIndex(SearchLuceneTasks bean);
    
}
