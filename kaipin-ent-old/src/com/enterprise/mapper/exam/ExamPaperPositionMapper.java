package com.enterprise.mapper.exam;

import com.enterprise.model.exam.ExamPaperPosition;

public interface ExamPaperPositionMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExamPaperPosition record);

    int insertSelective(ExamPaperPosition record);

    ExamPaperPosition selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamPaperPosition record);

    int updateByPrimaryKey(ExamPaperPosition record);
}