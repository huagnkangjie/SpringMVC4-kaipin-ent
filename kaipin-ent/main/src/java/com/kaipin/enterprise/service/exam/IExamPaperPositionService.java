package com.kaipin.enterprise.service.exam;

import com.kaipin.enterprise.model.exam.ExamPaperPosition;

public interface IExamPaperPositionService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(ExamPaperPosition record);

	public ExamPaperPosition selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(ExamPaperPosition record);
}