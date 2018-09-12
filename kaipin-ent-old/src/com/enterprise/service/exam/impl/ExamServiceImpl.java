package com.enterprise.service.exam.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.exam.ExamMapper;
import com.enterprise.service.exam.IExamService;

@Service("examService")
@Repository
public class ExamServiceImpl implements IExamService{
	
	@Autowired
	private ExamMapper mapper;

	@Override
	public int insertPaperQuestionRelation(Map<String, Object> map) {
		try {
			mapper.insertPaperQuestionRelation(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public List<Map<String, Object>> positionList(Map<String, Object> map) {
		try {
			return mapper.positionList(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> examCountList(Map<String, Object> map) {
		try {
			return mapper.examCountList(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> examCountListCount(Map<String, Object> map) {
		try {
			return mapper.examCountListCount(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> examPersonalList(Map<String, Object> map) {
		try {
			return mapper.examPersonalList(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> examPersonalListCount(Map<String, Object> map) {
		try {
			return mapper.examPersonalListCount(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> getPaperQuestionCount(Map<String, Object> map) {
		try {
			return mapper.getPaperQuestionCount(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> examAnswerDetailList(Map<String, Object> map) {
		try {
			return mapper.examAnswerDetailList(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> examQuestionList(Map<String, Object> map) {
		try {
			return mapper.examQuestionList(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> checkPaperHasUse(Map<String, Object> map) {
		try {
			return mapper.checkPaperHasUse(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> examAnswerDetailListCount(Map<String, Object> map) {
		try {
			return mapper.examAnswerDetailListCount(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateInviteStatus(Map<String, Object> map) {
		try {
			mapper.updateInviteStatus(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Map<String, Object> InviteDetail(Map<String, Object> map) {
		try {
			return mapper.InviteDetail(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deletePaperQuestionRelation(Map<String, Object> map) {
		try {
			mapper.deletePaperQuestionRelation(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> checkPaperByPos(Map<String, Object> map) {
		return mapper.checkPaperByPos(map);
	}

	@Override
	public List<Map<String, Object>> getPaperInvite(Map<String, Object> map) {
		return mapper.getPaperInvite(map);
	}

}
