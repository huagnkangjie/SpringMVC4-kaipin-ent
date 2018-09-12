package com.enterprise.mapper.login;

import java.util.List;
import java.util.Map;

import com.enterprise.model.EntBaseinfo;



public interface EntBaseinfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EntBaseinfo record);

    int insertSelective(EntBaseinfo record);

    EntBaseinfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntBaseinfo record);

    int updateByPrimaryKey(EntBaseinfo record);
    
    /**
     * 用户登录
     * @param map
     * @return
     */
    EntBaseinfo login(Map<String,Object> map);
    
    /**
     * 邮箱唯一性验证
     * @param map
     * @return
     */
    List<Map<String,Object>> emailValidata(Map<String,Object> map);
}