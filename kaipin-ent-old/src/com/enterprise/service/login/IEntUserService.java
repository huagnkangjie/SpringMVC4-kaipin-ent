package com.enterprise.service.login;

import java.util.List;
import java.util.Map;

import com.enterprise.model.common.EntUser;

public interface IEntUserService {
	
	public int deleteByPrimaryKey(String id);

	public int insertSelective(EntUser record);

	public EntUser selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(EntUser record);

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
