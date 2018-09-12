package com.kaipin.enterprise.service.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.exam.ExamQuestionDb;
import com.kaipin.enterprise.repository.dao.exam.IExamQuestionDbDao;
import com.kaipin.enterprise.service.exam.IExamQuestionDbService;

@Service("examQuestionDbService")
public class ExamQuestionDbServiceImpl implements IExamQuestionDbService{

	
	@Autowired
	private IExamQuestionDbDao mapper;
	
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
