package com.kaipin.enterprise.repository.dao.exam.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.exam.ExamQuestion;
import com.kaipin.enterprise.repository.dao.exam.IExamQuestionDao;

@Repository
public class ExamQuestionDaoImpl extends MybatisBaseDAO<ExamQuestion, String> implements IExamQuestionDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.exam.IExamQuestionDao";
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
	public int insertSelective(ExamQuestion record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public ExamQuestion selectByPrimaryKey(String id) {
		try {
			
			return (ExamQuestion) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(ExamQuestion record) {
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> questionList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deletePaperQuestion(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
