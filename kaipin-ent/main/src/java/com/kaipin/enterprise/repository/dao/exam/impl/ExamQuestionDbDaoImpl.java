package com.kaipin.enterprise.repository.dao.exam.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.exam.ExamQuestionDb;
import com.kaipin.enterprise.repository.dao.exam.IExamQuestionDbDao;

@Repository
public class ExamQuestionDbDaoImpl extends MybatisBaseDAO<ExamQuestionDb, String> implements IExamQuestionDbDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.exam.IExamQuestionDbDao";
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
	public int insertSelective(ExamQuestionDb record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public ExamQuestionDb selectByPrimaryKey(String id) {
		try {
			
			return (ExamQuestionDb) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(ExamQuestionDb record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
