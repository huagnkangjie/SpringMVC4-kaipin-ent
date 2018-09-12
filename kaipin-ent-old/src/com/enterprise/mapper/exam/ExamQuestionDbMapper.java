package com.enterprise.mapper.exam;

import com.enterprise.model.exam.ExamQuestionDb;

public interface ExamQuestionDbMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExamQuestionDb record);

    int insertSelective(ExamQuestionDb record);

    ExamQuestionDb selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamQuestionDb record);

    int updateByPrimaryKey(ExamQuestionDb record);
}