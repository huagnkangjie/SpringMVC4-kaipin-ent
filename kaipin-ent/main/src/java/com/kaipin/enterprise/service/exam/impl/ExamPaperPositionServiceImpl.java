package com.kaipin.enterprise.service.exam.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.exam.ExamPaperPosition;
import com.kaipin.enterprise.repository.dao.exam.IExamPaperPositionDao;
import com.kaipin.enterprise.service.exam.IExamPaperPositionService;

@Service("examPaperPositionService")
public class ExamPaperPositionServiceImpl implements IExamPaperPositionService{
	
	@Autowired
	private IExamPaperPositionDao mapper;
	
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
	public int insertSelective(ExamPaperPosition record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ExamPaperPosition selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ExamPaperPosition record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
