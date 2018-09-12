package com.mapper.user;

import java.util.List;
import java.util.Map;

import com.model.company.EntBaseUser;


public interface UserMapper {
	
	
	/**
	 * 根据企业id获取 企业管理员 和 企业的基本信息
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getUserAndEntInfo(Map<String,Object> map);
	
	/**
	 * 插入用户中间关系表
	 * @param record
	 * @return
	 */
	int insertSelective(EntBaseUser record);
	
	/**
	 * 邮箱校验
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> emailValidata(Map<String,Object> map);
	
	/**
	 * 手机号唯一性
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> validataPhone(Map<String,Object> map);
	
	/**
	 * 登录
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> login(Map<String,Object> map);
	
	/**
	 * 邮箱发过来的验证
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> cheackmail(Map<String,Object> map);
	
	/**
	 * 重新发送邮件，获取用户信息
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> againMail(Map<String,Object> map);
	
	/**
	 * 获取审核反馈意见
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getFeedbackList(Map<String,Object> map);
	
	/**
	 * 邀请码唯一性验证
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> validataInviteCode(Map<String,Object> map);
	
}