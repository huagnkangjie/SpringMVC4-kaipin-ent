package com.kaipin.enterprise.service.exam;

import com.kaipin.enterprise.model.exam.ExamQuestionDb;

public interface IExamQuestionDbService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(ExamQuestionDb record);

	public ExamQuestionDb selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(ExamQuestionDb record);
}