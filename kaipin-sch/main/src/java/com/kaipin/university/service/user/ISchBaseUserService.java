package com.kaipin.university.service.user;

import java.util.List;
import java.util.Map;

import com.kaipin.university.model.user.UserLocalauth;

public interface ISchBaseUserService {
	/**
	 * 获取关注和粉丝
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getFollowAndFans(Map<String,Object> map);
	
	/**
	 * 获取关注和粉丝列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getFollowAndFansList(Map<String,Object> map);
	/**
	 * 获取资质认证
	 * @param uid
	 * @return
	 */
	public List<Map<String,Object>> selectDoc(String uid);
	
	public int insertDoc(Map<String,Object> map);
	
	public int deleteDoc(Map<String,Object> map);
	
	/**
	 * 验证手机的唯一性
	 * @param phone
	 * @return
	 */
	public List<Map<String,Object>> validataPhone(String phone);
	
	/**
	 * 根据用户id获取企业基本信息
	 * @param uid
	 * @return
	 */
	public List<Map<String,Object>> getCompanyInfoByUserId(String uid);
	
	/**
	 * 根据用户id获取学校基本信息
	 * @param uid
	 * @return
	 */
	public List<Map<String,Object>> getSchoolInfoByUserId(String uid);
	
	/**
	 * 根据用户id获取学生基本信息
	 * @param uid
	 * @return
	 */
	public List<Map<String,Object>> getStuInfoByUserId(String uid);
	
	/**
	 * 获取当前用户的推荐列表
	 * @param uid
	 * @param orgId
	 * @return
	 */
	public List<Map<String,Object>> getPushEntList(Map<String,Object> map);
	
	public List<Map<String,Object>> getPushSchList(Map<String,Object> map);
	
	public List<Map<String,Object>> getPushStuList(Map<String,Object> map);
	
	
	/**
	 * 添加推荐记录
	 * @param map
	 * @return
	 */
	public boolean insertFollowRecommendEnt(Map<String,Object> map);
	public boolean insertFollowRecommendSch(Map<String,Object> map);
	public boolean insertFollowRecommendStu(Map<String,Object> map);
	
	/**
	 * 判断某个用户是否关注了某用户
	 * @param fromUid 用户
	 * @param toUid 被关注的用户
	 * @return
	 */
	public List<Map<String,Object>> checkIsFollow(Map<String,Object> map);
	
	/**
	 * 添加关注
	 * @param map
	 * @return
	 */
	public int addPushFoloow(Map<String,Object> map);
	
	/**
	 * 删除关注
	 * @param map
	 * @return
	 */
	public int delPushFoloow(Map<String,Object> map);
	
	/**
	 * 更新关注关系
	 * @param map
	 * @return
	 */
	public int updatePushFoloow(Map<String,Object> map);
	
	/**
	 * 获取审核反馈信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getFeedbackList(Map<String,Object> map);
	
	/**
	 * 检查企业是否进行了资质认证
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> checkCertificate(Map<String,Object> map);
}
