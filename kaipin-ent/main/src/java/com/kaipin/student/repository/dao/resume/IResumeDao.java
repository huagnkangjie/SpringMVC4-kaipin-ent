package com.kaipin.student.repository.dao.resume;

import java.util.List;
import java.util.Map;

import com.kaipin.student.model.resume.ResumeInfo;

public interface IResumeDao {

	ResumeInfo getUserResume(String id);

	void addUserResume(ResumeInfo area);
	
	List<ResumeInfo> selectAll();
	
	int deleteByPrimaryKey(String id);
	
	List<Map<String,Object>> getCounts(Map<String,Object> map);
	
	 /**
     * 根据 已读 未读 已通过 未通过 等条件
     * 获取职位列表，并且获取该职位下的简历数
     * datagrid
     * @param map
     * @return
     */
	List<Map<String,Object>> getCountsOfPostionList(Map<String,Object> map);
    
	int getCountsOfPostionListTotal(Map<String,Object> map);
	/**
     * 根据 已读 未读 已通过 未通过 等条件
     * 获取某个职位所搜到的全部简历
     * datagrid
     * @param map
     * @return
     */
	List<Map<String,Object>> datagridNoReadList(Map<String,Object> map);
	
	int datagridNoReadListTotal(Map<String,Object> map);
	
	 /**
     * 获取每个企业条件筛选的城市
     * @param map
     * @return
     */
	List<Map<String,Object>> cityList(Map<String,Object> map);
	
	/**
     * 获取每个企业的工作类型列表
     * @param map
     * @return
     */
	List<Map<String,Object>> jobTypeList(Map<String,Object> map);
	/**
     * 全局搜索简历
     * @param map
     * @return
     */
	List<Map<String,Object>> search(Map<String,Object> map);
	
	/**
     * 获取一个企业的最近搜到的所有未读简历
     * @param map
     * @return
     */
	List<Map<String,Object>> getAll(Map<String,Object> map);
	
	 /**
     * 获取工作区域
     * @param map
     * @return
     */
	List<Map<String,Object>> getWorkAreaList(Map<String,Object> map);
    
    /**
     * 获取喜欢的工作类型
     * @param map
     * @return
     */
	List<Map<String,Object>> getLikeJobTypeList(Map<String,Object> map);
    
    /**
     * 获取聘用类型
     * @param map
     * @return
     */
	List<Map<String,Object>> getEmployTypeList(Map<String,Object> map);
    
    /**
     * 获取语言
     * @param map
     * @return
     */
	List<Map<String,Object>> getlanguageList(Map<String,Object> map);
	
	/**
     * 企业首页简历总数统计
     * @return
     */
	List<Map<String,Object>> getIndexResumeListCounts(Map<String,Object> map);
	
	/**
     * 插入面试日志
     * @param map
     * @return
     */
	int insertViewLog(Map<String,Object> map);
	
	/**
     * 简历详情，左边按钮
     * 第n次m面
     * @param map
     * @return
     */
	List<Map<String,Object>> getFaceTimes(Map<String,Object> map);
	
	/**
     * 检查学生简历是否有邮箱
     * @param map
     * @return
     */
	List<Map<String,Object>> checkResumeEmail(Map<String,Object> map);
	
	/**
     * 获取当前简历状态
     * @param map
     * @return
     */
	List<Map<String,Object>> getCurrentStatus(Map<String,Object> map);
	
	/**
	 * 获取日志list
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getLogList(Map<String,Object> map);
	
}
