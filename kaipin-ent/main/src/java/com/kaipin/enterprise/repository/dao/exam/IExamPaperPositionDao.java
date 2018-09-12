package com.kaipin.enterprise.repository.dao.exam;

import com.kaipin.enterprise.model.exam.ExamPaperPosition;

public interface IExamPaperPositionDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ExamPaperPosition record);

    ExamPaperPosition selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamPaperPosition record);
}