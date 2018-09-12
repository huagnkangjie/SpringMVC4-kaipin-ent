package com.enterprise.mapper.exam;

import com.enterprise.model.exam.ExamPaper;

public interface ExamPaperMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExamPaper record);

    int insertSelective(ExamPaper record);

    ExamPaper selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamPaper record);

    int updateByPrimaryKey(ExamPaper record);
}