package com.kaipin.enterprise.repository.dao.exam;

import com.kaipin.enterprise.model.exam.ExamQuestionDb;

public interface IExamQuestionDbDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ExamQuestionDb record);

    ExamQuestionDb selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamQuestionDb record);
}