package com.kaipin.search.repository.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.search.repository.dao.SearchExpireObjectDao;
import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class SearchExpireObjectDaoImpl extends MybatisBaseDAO<Map, String> implements  SearchExpireObjectDao  {

    @Override
    public String getDefaultSqlNamespace() {
   
        return "SearchExpireObjectMapper";
    }

    @Override
    public int insertLog(Map<String, Object> map) {
      
        return this.insert("insertLog", map);
    }

 
    @Override
    public int deleteLog(String obj_id) {
   
        return this.deleteById("deleteLog", obj_id);
    }

}
