package com.kaipin.enterprise.repository.dao.exam;

import com.kaipin.enterprise.model.exam.ExamPaper;

public interface IExamPaperDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ExamPaper record);

    ExamPaper selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamPaper record);

}