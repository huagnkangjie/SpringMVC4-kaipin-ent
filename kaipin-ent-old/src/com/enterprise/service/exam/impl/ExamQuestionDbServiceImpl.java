package com.enterprise.service.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.exam.ExamQuestionDbMapper;
import com.enterprise.model.exam.ExamQuestionDb;
import com.enterprise.service.exam.IExamQuestionDbService;

@Service("examQuestionDbService")
@Repository
public class ExamQuestionDbServiceImpl implements IExamQuestionDbService{
	
	@Autowired
	private ExamQuestionDbMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(ExamQuestionDb record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ExamQuestionDb selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ExamQuestionDb record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
