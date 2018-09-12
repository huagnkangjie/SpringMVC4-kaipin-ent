package com.kaipin.enterprise.service.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.exam.ExamPaper;
import com.kaipin.enterprise.repository.dao.exam.IExamPaperDao;
import com.kaipin.enterprise.service.exam.IExamPaperService;

@Service("examPaperService")
public class ExamPaperServiceImpl implements IExamPaperService{

	
	@Autowired
	private IExamPaperDao mapper;
	
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
	public int insertSelective(ExamPaper record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ExamPaper selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ExamPaper record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
