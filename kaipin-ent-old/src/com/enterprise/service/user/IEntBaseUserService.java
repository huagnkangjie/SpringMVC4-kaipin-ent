package com.enterprise.service.user;

import java.util.List;
import java.util.Map;

import com.enterprise.model.user.CompanyInfo;
import com.enterprise.model.user.CompanyUserInfo;
import com.enterprise.model.user.EntBaseUser;

public interface IEntBaseUserService {
	
	/**
	 * 根据企业id获取 企业管理员 和 企业的基本信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUserAndEntInfo(Map<String,Object> map);
	
	/**
	 * 插入用户中间关系表
	 * @param record
	 * @return
	 */
	public int insertSelective(EntBaseUser record);
	
	/**
	 * 创建用户基本信息
	 * user表
	 * 企业基本信息表
	 * 关联表
	 * @param userInfo
	 * @return
	 */
	public int createEntUser(CompanyUserInfo userInfo, CompanyInfo info);
	
	/**
	 * 邮箱校验
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> emailValidata(Map<String,Object> map);
	
	
	/**
	 * 手机号唯一性
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> validataPhone(Map<String,Object> map);
	
	/**
	 * 登录
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> login(Map<String,Object> map);
	
	/**
	 * 邮箱发过来的验证
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> cheackmail(Map<String,Object> map);
	
	/**
	 * 添加资质认证
	 * @param map
	 * @return
	 */
	public int insertDoc(Map<String,Object> map);
	
	/**
	 * 删除资质认证
	 * @param map
	 * @return
	 */
	public int deleteDoc(Map<String,Object> map);
	
	/**
	 * 更新企业资质表
	 * @param map
	 * @return
	 */
	public int updateDoc(Map<String,Object> map);
	
	/**
	 * 添加资质认证历史
	 * @param map
	 * @return
	 */
	public int insertDocHistory(Map<String,Object> map);
	
	/**
	 * 查询资质认证
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> selectDoc(Map<String,Object> map);
	
	/**
	 * 查询资质认证历史
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> selectDocHistory(Map<String,Object> map);
	
	/**
	 * 重新发送邮件，获取用户信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> againMail(Map<String,Object> map);
	
	/**
	 * 获取审核反馈意见
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getFeedbackList(Map<String,Object> map);
}
