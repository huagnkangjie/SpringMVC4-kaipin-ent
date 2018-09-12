package com.kaipin.search.repository.dao;

import java.util.Map;

public interface SearchExpireObjectDao {

    int insertLog(Map<String, Object> map);
    
    public int deleteLog(String obj_id);
    
}
