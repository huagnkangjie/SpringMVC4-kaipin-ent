package com.enterprise.service.login;

import java.util.List;
import java.util.Map;

import com.enterprise.model.EntBaseinfo;


public interface IEntBaseinfoService {
	/**
	 * 企业用户登录
	 * @param map
	 * @return
	 */
	public EntBaseinfo login(Map<String,Object> map);
	
	/**
	 * 注册
	 * @param record
	 * @return
	 */
	public int insertSelective(EntBaseinfo record);
	/**
     * 邮箱唯一性验证
     * @param map
     * @return
     */
	public List<Map<String,Object>> emailValidata(Map<String,Object> map);
	
	public int deleteByPrimaryKey(String id);
	
	public int updateByPrimaryKeySelective(EntBaseinfo record);
	
	public EntBaseinfo selectByPrimaryKey(String id);
	
	
}
