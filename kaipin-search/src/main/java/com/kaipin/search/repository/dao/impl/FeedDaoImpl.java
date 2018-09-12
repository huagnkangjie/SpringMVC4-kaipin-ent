package com.kaipin.search.repository.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.search.repository.dao.FeedDao;
import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class FeedDaoImpl   extends MybatisBaseDAO<Map, String> implements FeedDao{

    @Override
    public int deleteByResourceId(String resource_id) {
         
        return delete("deleteByResourceId", resource_id);
    }

    @Override
    public int updateLogicDelByResourceId(String resource_id) {
        
      return update("updateLogicDelByResourceId", resource_id);
    }

    @Override
    public String getDefaultSqlNamespace() {
       
        return "FeedMapper";
    }

}
