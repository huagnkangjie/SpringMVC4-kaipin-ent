package com.enterprise.service.resume.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.resume.UserResumeMapper;
import com.enterprise.model.UserResume;
import com.enterprise.pojo.CountHomeBean;
import com.enterprise.service.resume.IResumeService;

@Service("resumeService")
@Repository
public class ResumeServiceImpl implements IResumeService{
	
	@Autowired
	private UserResumeMapper mapper;

	@Override
	public UserResume getUserResume(String id)
	{
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void addUserResume(UserResume UserResume)
	{
		mapper.insertSelective(UserResume);
	}

	@Override
	public List<UserResume> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return mapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Map<String,Object>> getCounts(Map<String,Object> map) {
		return mapper.getCounts(map);
	}

	@Override
	public List<Map<String, Object>> getCountsOfPostionList(
			Map<String, Object> map) {
		return mapper.getCountsOfPostionList(map);
	}

	@Override
	public int getCountsOfPostionListTotal(Map<String, Object> map) {
		return mapper.getCountsOfPostionListTotal(map);
	}

	@Override
	public List<Map<String, Object>> datagridNoReadList(Map<String, Object> map) {
		return mapper.datagridNoReadList(map);
	}

	@Override
	public int datagridNoReadListTotal(Map<String, Object> map) {
		return mapper.datagridNoReadListTotal(map);
	}

	@Override
	public List<Map<String, Object>> cityList(Map<String, Object> map) {
		return mapper.cityList(map);
	}

	@Override
	public List<Map<String, Object>> jobTypeList(Map<String, Object> map) {
		return mapper.jobTypeList(map);
	}

	@Override
	public List<Map<String, Object>> search(Map<String, Object> map) {
		return mapper.search(map);
	}

	@Override
	public List<Map<String,Object>> getAll(Map<String, Object> map) {
		return mapper.getAll(map);
	}

	@Override
	public List<Map<String, Object>> getWorkAreaList(Map<String, Object> map) {
		return mapper.getWorkAreaList(map);
	}

	@Override
	public List<Map<String, Object>> getLikeJobTypeList(Map<String, Object> map) {
		return mapper.getLikeJobTypeList(map);
	}

	@Override
	public List<Map<String, Object>> getEmployTypeList(Map<String, Object> map) {
		return mapper.getEmployTypeList(map);
	}

	@Override
	public List<Map<String, Object>> getlanguageList(Map<String, Object> map) {
		return mapper.getlanguageList(map);
	}

	@Override
	public List<Map<String, Object>> getIndexResumeListCounts(Map<String, Object> map) {
		return mapper.getIndexResumeListCounts(map);
	}

	@Override
	public int insertViewLog(Map<String, Object> map) {
		return mapper.insertViewLog(map);
	}

	@Override
	public List<Map<String, Object>> getFaceTimes(Map<String, Object> map) {
		return mapper.getFaceTimes(map);
	}

	@Override
	public List<Map<String, Object>> checkResumeEmail(Map<String, Object> map) {
		return mapper.checkResumeEmail(map);
	}

	@Override
	public List<Map<String, Object>> getCurrentStatus(Map<String, Object> map) {
		return mapper.getCurrentStatus(map);
	}

}
