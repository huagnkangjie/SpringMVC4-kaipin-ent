package com.kaipin.university.repository.dao.user;

import java.util.List;
import java.util.Map;

import com.kaipin.university.model.user.UserLocalauth;

public interface ISchBaseUserDao {

	public List<Map<String,Object>> getFollowAndFans(Map<String,Object> map);
	
	public List<Map<String,Object>> getFollowAndFansList(Map<String,Object> map);
	
	public List<Map<String,Object>> selectDoc(String uid);
	
	public int insertDoc(Map<String,Object> map);
	
	public int deleteDoc(Map<String,Object> map);
	
	public List<Map<String,Object>> validataPhone(String phone);
	
	public List<Map<String,Object>> getCompanyInfoByUserId(String uid);
	
	public List<Map<String,Object>> getSchoolInfoByUserId(String uid);
	
	public List<Map<String,Object>> getStuInfoByUserId(String uid);
	
	/**
	 * 获取推荐列表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getPushEntList(Map<String,Object> map);
	List<Map<String,Object>> getPushSchList(Map<String,Object> map);
	List<Map<String,Object>> getPushStuList(Map<String,Object> map);
	
	/**
	 * 添加推荐记录
	 * @param map
	 * @return
	 */
	boolean insertFollowRecommendEnt(Map<String,Object> map);
	boolean insertFollowRecommendSch(Map<String,Object> map);
	boolean insertFollowRecommendStu(Map<String,Object> map);
	
	
	/**
	 * 判断某个用户是否关注了某用户
	 * @param fromUid 用户
	 * @param toUid 被关注的用户
	 * @return
	 */
	List<Map<String,Object>> checkIsFollow(Map<String,Object> map);
	
	/**
	 * 添加关注
	 * @param map
	 * @return
	 */
	int addPushFoloow(Map<String,Object> map);
	
	/**
	 * 删除关注
	 * @param map
	 * @return
	 */
	int delPushFoloow(Map<String,Object> map);
	
	/**
	 * 更新关注关系
	 * @param map
	 * @return
	 */
	int updatePushFoloow(Map<String,Object> map);
	
	/**
	 * 获取审核反馈信息
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getFeedbackList(Map<String,Object> map);
	
	
	/**
	 * 检查企业是否进行了资质认证
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> checkCertificate(Map<String,Object> map);
	
}
