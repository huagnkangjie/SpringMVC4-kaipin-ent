package com.kaipin.enterprise.repository.dao.exam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.repository.dao.exam.IExamDao;

@SuppressWarnings("unchecked")
@Repository
public class ExamDaoImpl extends MybatisBaseDAO<Object, String> implements IExamDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.exam.IExamDao";
	}
	
	@Override
	public List<Map<String, Object>> positionList(Map<String, Object> map) {
		return this.selectList("positionList", map);
	}

	@Override
	public List<Map<String, Object>> examCountList(Map<String, Object> map) {
		return this.selectList("examCountList", map);
	}

	@Override
	public List<Map<String, Object>> examCountListCount(Map<String, Object> map) {
		return this.selectList("examCountListCount", map);
	}

	@Override
	public List<Map<String, Object>> examPersonalList(Map<String, Object> map) {
		return this.selectList("examPersonalList", map);
	}

	@Override
	public Map<String, Object> examPersonalListCount(Map<String, Object> map) {
		
		List<Map<String,Object>> list = this.selectList("examPersonalListCount", map);
		Map<String,Object> result = new HashMap<>();
		if(list.size() > 0){
			result = list.get(0);
		}
		return result;
		
	}

	@Override
	public int insertPaperQuestionRelation(Map<String, Object> map) {
		try {
			this.insert("insertPaperQuestionRelation", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deletePaperQuestionRelation(Map<String, Object> map) {
		try {
			this.delete("deletePaperQuestionRelation", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Map<String, Object> getPaperQuestionCount(Map<String, Object> map) {
		
		List<Map<String,Object>> list = this.selectList("getPaperQuestionCount", map);
		Map<String,Object> result = new HashMap<>();
		if(list.size() > 0){
			result = list.get(0);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> examAnswerDetailList(Map<String, Object> map) {
		return this.selectList("examAnswerDetailList", map);
	}

	@Override
	public List<Map<String, Object>> examAnswerDetailListCount(Map<String, Object> map) {
		return this.selectList("examAnswerDetailListCount", map);
	}

	@Override
	public int updateInviteStatus(Map<String, Object> map) {
		try {
			this.update("updateInviteStatus", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Map<String, Object> InviteDetail(Map<String, Object> map) {
		List<Map<String,Object>> list = this.selectList("InviteDetail", map);
		Map<String,Object> result = new HashMap<>();
		if(list.size() > 0){
			result = list.get(0);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> examQuestionList(Map<String, Object> map) {
		return this.selectList("examQuestionList", map);
	}

	@Override
	public List<Map<String, Object>> checkPaperHasUse(Map<String, Object> map) {
		return this.selectList("checkPaperHasUse", map);
	}

	@Override
	public List<Map<String, Object>> checkPaperByPos(Map<String, Object> map) {
		return this.selectList("checkPaperByPos", map);
	}

	@Override
	public List<Map<String, Object>> getPaperInvite(Map<String, Object> map) {
		return this.selectList("getPaperInvite", map);
	}

	

}
