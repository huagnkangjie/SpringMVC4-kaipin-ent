package com.kaipin.enterprise.repository.dao.exam.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.exam.ExamPaper;
import com.kaipin.enterprise.repository.dao.exam.IExamPaperDao;

@Repository
public class ExamPaperDaoImpl extends MybatisBaseDAO<ExamPaper, String> implements IExamPaperDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.exam.IExamPaperDao";
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
	public int insertSelective(ExamPaper record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public ExamPaper selectByPrimaryKey(String id) {
		try {
			
			return (ExamPaper) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(ExamPaper record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
