package com.enterprise.mapper.login;

import java.util.List;
import java.util.Map;

import com.enterprise.model.common.EntUser;


public interface EntUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(EntUser record);

    int insertSelective(EntUser record);

    EntUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntUser record);

    int updateByPrimaryKey(EntUser record);
    
    /**
	 * 企业用户登录
	 * @param map
	 * @return
	 */
	public EntUser login(Map<String,Object> map);
	
	
	/**
	 * 获取首页 基本信息换个
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUserInfoAndConfig(Map<String,Object> map);
}