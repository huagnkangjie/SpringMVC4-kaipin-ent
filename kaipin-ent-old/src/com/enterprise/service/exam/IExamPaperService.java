package com.enterprise.service.exam;

import com.enterprise.model.exam.ExamPaper;

public interface IExamPaperService {
	public int deleteByPrimaryKey(String id);


	public int insertSelective(ExamPaper record);

	public ExamPaper selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(ExamPaper record);


}
