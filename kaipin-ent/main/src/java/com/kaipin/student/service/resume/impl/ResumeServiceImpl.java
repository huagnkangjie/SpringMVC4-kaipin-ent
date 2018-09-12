package com.kaipin.student.service.resume.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.model.resume.ResumeInfo;
import com.kaipin.student.repository.dao.resume.IResumeDao;
import com.kaipin.student.service.resume.IResumeService;


@Service("resumeService")
public class ResumeServiceImpl implements IResumeService{
	
	@Autowired
	private IResumeDao dao;

	@Override
	public ResumeInfo getUserResume(String id) {
		return dao.getUserResume(id);
	}

	@Override
	public void addUserResume(ResumeInfo area) {
		dao.addUserResume(area);
		
	}

	@Override
	public List<ResumeInfo> selectAll() {
		return dao.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Map<String, Object>> getCounts(Map<String, Object> map) {
		return dao.getCounts(map);
	}

	@Override
	public List<Map<String, Object>> getCountsOfPostionList(Map<String, Object> map) {
		return dao.getCountsOfPostionList(map);
	}

	@Override
	public int getCountsOfPostionListTotal(Map<String, Object> map) {
		return dao.getCountsOfPostionListTotal(map);
	}

	@Override
	public List<Map<String, Object>> datagridNoReadList(Map<String, Object> map) {
		return dao.datagridNoReadList(map);
	}

	@Override
	public int datagridNoReadListTotal(Map<String, Object> map) {
		return dao.datagridNoReadListTotal(map);
	}

	@Override
	public List<Map<String, Object>> cityList(Map<String, Object> map) {
		return dao.cityList(map);
	}

	@Override
	public List<Map<String, Object>> jobTypeList(Map<String, Object> map) {
		return dao.jobTypeList(map);
	}

	@Override
	public List<Map<String, Object>> search(Map<String, Object> map) {
		return dao.search(map);
	}

	@Override
	public List<Map<String, Object>> getAll(Map<String, Object> map) {
		return dao.getAll(map);
	}

	@Override
	public List<Map<String, Object>> getWorkAreaList(Map<String, Object> map) {
		return dao.getWorkAreaList(map);
	}

	@Override
	public List<Map<String, Object>> getLikeJobTypeList(Map<String, Object> map) {
		return dao.getLikeJobTypeList(map);
	}

	@Override
	public List<Map<String, Object>> getEmployTypeList(Map<String, Object> map) {
		return dao.getEmployTypeList(map);
	}

	@Override
	public List<Map<String, Object>> getlanguageList(Map<String, Object> map) {
		return dao.getlanguageList(map);
	}

	@Override
	public List<Map<String, Object>> getIndexResumeListCounts(Map<String, Object> map) {
		return dao.getIndexResumeListCounts(map);
	}

	@Override
	public int insertViewLog(Map<String, Object> map) {
		return dao.insertViewLog(map);
	}

	@Override
	public List<Map<String, Object>> getFaceTimes(Map<String, Object> map) {
		return dao.getFaceTimes(map);
	}

	@Override
	public List<Map<String, Object>> checkResumeEmail(Map<String, Object> map) {
		return dao.checkResumeEmail(map);
	}

	@Override
	public List<Map<String, Object>> getCurrentStatus(Map<String, Object> map) {
		return dao.getCurrentStatus(map);
	}

	@Override
	public List<Map<String, Object>> getLogList(Map<String, Object> map) {
		return dao.getLogList(map);
	}

}
