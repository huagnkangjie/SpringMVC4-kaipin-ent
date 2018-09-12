package com.kaipin.search.repository.dao;

public interface FeedDao {

    
    public int deleteByResourceId(String resource_id);
    
    public int  updateLogicDelByResourceId(String resource_id);
}
