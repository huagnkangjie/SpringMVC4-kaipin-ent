package com.kaipin.enterprise.service.exam;

import java.util.List;
import java.util.Map;

import com.kaipin.enterprise.model.exam.ExamListBean;
import com.kaipin.enterprise.model.exam.ExamQuestion;

public interface IExamQuestionService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(ExamQuestion record);

	public ExamQuestion selectByPrimaryKey(String id);

	public  int updateByPrimaryKeySelective(ExamQuestion record);
    
    /**
     * 获取试卷下问题list
     * @param map
     * @return
     */
	public List<Map<String,Object>> questionList(Map<String,Object> map);
    
    /**
     * 删除试卷下的所有问题
     * @param map
     * @return
     */
	public int deletePaperQuestion(Map<String,Object> map);

    
    /**
	 * 更新卷子
	 * @return
	 */
	public int updateExamPaperQuestions(ExamListBean examList, String paperId, String editFlag, String dbId);

	
	/**
	 * 批量插入试卷的所有问题
	 * @param examList
	 * @return
	 */
	public int insertExamList(ExamListBean examList, String companyId, String positionId);
    
}