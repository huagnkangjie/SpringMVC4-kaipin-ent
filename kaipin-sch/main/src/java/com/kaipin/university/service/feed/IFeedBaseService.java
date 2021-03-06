package com.kaipin.university.service.feed;

import java.util.List;
import java.util.Map;

public interface IFeedBaseService {

	/**
	 * 获取信息流
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getFeeds(Map<String, Object> map);
	
	/**
	 * 获取（某个组织）信息流
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getFeedByOrgId(Map<String, Object> map);
	
	/**
	 * 获取条数的总数
	 * @param map
	 * @return
	 */
	public int getFeedsCount(Map<String, Object> map);
	
	/**
	 * 获取条数的总数
	 * @param map
	 * @return
	 */
	public int getFeedByOrgIdCount(Map<String, Object> map);
	
	/**
	 * 获取职位
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getPosition(String id);
	
	/**
	 * 获取宣讲会和视频
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getLiveInfo(String id);
	
	/**
	 * 获取企业信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getCompanyInfo(String id);
	
	/**
	 * 获取学校
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getSchInfo(String id);
	
	/**
	 * 获取学生信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getStuInfo(String id);
	
	/**
	 * 获取点赞总数
	 * @param map
	 * @return
	 */
	public int getFeedZanCount(String id);
	
	/**
	 * 判断用户是否对消息进行了点赞
	 * @param map
	 * @return
	 */
	public int checkFeedZan(Map<String, Object> map);
	
	/**
	 * 对消息点赞
	 * @param map
	 * @return
	 */
	public int insertFeedZan(Map<String,Object> map);
	
	/**
	 * 取消赞
	 * @param id
	 * @return
	 */
	public int delFeedZan(Map<String,Object> map);
	
	
	/**
	 * 获取一级回复
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getCommentsLevelOne(String id);

	/**
	 * 获取二级回复
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getCommentsLevelTwo(String id);
	
	/**
	 * 删除动态，根据资源id
	 * @param resourceId
	 * @return
	 */
	public int delFeedByResourceId(String resourceId);
	
	/**
	 * 更新评论状态 （逻辑删除）
	 * @param feedId
	 * @return
	 */
	public int deleteFeedComment(Map<String,Object> map);
	
	/**
	 * 根据评论id删除一级评论
	 * @param feedId
	 * @return
	 */
	public int deleteCommentById(String commentId);
	
	/**
	 * 根据评论id删除二级评论
	 * @param feedId
	 * @return
	 */
	public int deleteCommentByParentId(String commentId);
	
	/**
	 * 获取当前一级评论的二级回复总数
	 * @param commentId
	 * @return
	 */
	public int getCommentLevelTowCount(String commentId);
}
