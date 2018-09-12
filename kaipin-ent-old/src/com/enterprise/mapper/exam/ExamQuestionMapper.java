package com.enterprise.mapper.exam;

import java.util.List;
import java.util.Map;

import com.enterprise.model.exam.ExamQuestion;

public interface ExamQuestionMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExamQuestion record);

    int insertSelective(ExamQuestion record);

    ExamQuestion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamQuestion record);

    int updateByPrimaryKey(ExamQuestion record);
    
    /**
     * 获取试卷下问题list
     * @param map
     * @return
     */
    List<Map<String,Object>> questionList(Map<String,Object> map);
    
    /**
     * 删除试卷下的所有问题
     * @param map
     * @return
     */
    int deletePaperQuestion(Map<String,Object> map);
    
}