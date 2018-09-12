package com.kaipin.enterprise.repository.dao.exam.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.exam.ExamPaperPosition;
import com.kaipin.enterprise.repository.dao.exam.IExamPaperPositionDao;

@Repository
public class ExamPaperPositionDaoImpl extends MybatisBaseDAO<ExamPaperPosition, String> implements IExamPaperPositionDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.exam.IExamPaperPositionDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			this.delete("deleteByPrimaryKey", id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(ExamPaperPosition record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public ExamPaperPosition selectByPrimaryKey(String id) {
		try {
			
			return (ExamPaperPosition) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(ExamPaperPosition record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
