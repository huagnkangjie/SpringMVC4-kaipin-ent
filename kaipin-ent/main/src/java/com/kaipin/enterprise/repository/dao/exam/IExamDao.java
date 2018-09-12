package com.kaipin.enterprise.repository.dao.exam;

import java.util.List;
import java.util.Map;


public interface IExamDao {
	
	/**
	 * 获取没有创建笔试题的职位列表
	 * @return
	 */
	List<Map<String,Object>> positionList(Map<String,Object> map); 
	
	/**
	 * 笔试题统计列表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> examCountList(Map<String,Object> map);
	
	List<Map<String,Object>> examCountListCount(Map<String,Object> map);
	
	/**
	 * 获取某个职位做题的人员列表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> examPersonalList(Map<String,Object> map);
	
	Map<String,Object> examPersonalListCount(Map<String,Object> map);
	
	
	/**
	 * 创建试卷与问题关联
	 * @param map
	 * @return
	 */
	int insertPaperQuestionRelation(Map<String,Object> map);
	
	/**
	 * 删除所有问题与试卷的关联
	 * @param map
	 * @return
	 */
	int deletePaperQuestionRelation(Map<String,Object> map);
	
	/**
	 * 获取试卷题目总数
	 * @param map
	 * @return
	 */
	Map<String,Object> getPaperQuestionCount(Map<String,Object> map);
	
	
	/**
	 * 答题人答题详情
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> examAnswerDetailList(Map<String,Object> map);
	
	List<Map<String,Object>> examAnswerDetailListCount(Map<String,Object> map);
	
	/**
	 * 更新邀请状态
	 * @param map
	 * @return
	 */
	int updateInviteStatus(Map<String,Object> map);
	
	/**
	 * 邀请记录详情
	 * @param map
	 * @return
	 */
	Map<String,Object> InviteDetail(Map<String,Object> map);
	
	/**
	 * 试卷详情
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> examQuestionList(Map<String,Object> map);
	
	/**
	 * 判断试卷是已经被使用
	 * 
	 * 如果已经被使用则不能对问卷进行题目数量的增删
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> checkPaperHasUse(Map<String,Object> map);
	
	/**
	 * 判断职位是否存在笔试题
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> checkPaperByPos(Map<String,Object> map);
	
	/**
	 * 获取邀请记录
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getPaperInvite(Map<String,Object> map);
	
}
