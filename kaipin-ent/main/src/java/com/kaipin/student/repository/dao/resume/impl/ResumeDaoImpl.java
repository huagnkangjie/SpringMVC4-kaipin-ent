package com.kaipin.student.repository.dao.resume.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.student.model.resume.ResumeInfo;
import com.kaipin.student.repository.dao.resume.IResumeDao;

@Repository
@SuppressWarnings("unchecked")
public class ResumeDaoImpl extends MybatisBaseDAO<ResumeInfo, String> implements IResumeDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.student.repository.dao.resume.IResumeDao";
	}

	@Override
	public ResumeInfo getUserResume(String id) {
		return (ResumeInfo) this.selectOne("getUserResume", id);
	}

	@Override
	public void addUserResume(ResumeInfo area) {
	}

	@Override
	public List<ResumeInfo> selectAll() {
		return this.selectList("selectAll");
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public List<Map<String, Object>> getCounts(Map<String, Object> map) {
		return this.selectList("getCounts", map);
	}

	@Override
	public List<Map<String, Object>> getCountsOfPostionList(Map<String, Object> map) {
		return this.selectList("getCountsOfPostionList", map);
	}

	@Override
	public int getCountsOfPostionListTotal(Map<String, Object> map) {
		int count = 0;
		List<Map<String, Object>> list = this.selectList("getCountsOfPostionListTotal", map);
		if(list.size() > 0){
			count = Integer.valueOf(list.get(0) + "");
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> datagridNoReadList(Map<String, Object> map) {
		return this.selectList("datagridNoReadList", map);
	}

	@Override
	public int datagridNoReadListTotal(Map<String, Object> map) {
		int count = 0;
		List<Map<String, Object>> list = this.selectList("datagridNoReadListTotal", map);
		if(list.size() > 0){
			count = Integer.valueOf(list.get(0)+ "");
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> cityList(Map<String, Object> map) {
		return this.selectList("cityList", map);
	}

	@Override
	public List<Map<String, Object>> jobTypeList(Map<String, Object> map) {
		return this.selectList("jobTypeList", map);
	}

	@Override
	public List<Map<String, Object>> search(Map<String, Object> map) {
		return this.selectList("search", map);
	}

	@Override
	public List<Map<String, Object>> getAll(Map<String, Object> map) {
		return this.selectList("getAll", map);
	}

	@Override
	public List<Map<String, Object>> getWorkAreaList(Map<String, Object> map) {
		return this.selectList("getWorkAreaList", map);
	}

	@Override
	public List<Map<String, Object>> getLikeJobTypeList(Map<String, Object> map) {
		return this.selectList("getLikeJobTypeList", map);
	}

	@Override
	public List<Map<String, Object>> getEmployTypeList(Map<String, Object> map) {
		return this.selectList("getEmployTypeList", map);
	}

	@Override
	public List<Map<String, Object>> getlanguageList(Map<String, Object> map) {
		return this.selectList("getlanguageList", map);
	}

	@Override
	public List<Map<String, Object>> getIndexResumeListCounts(Map<String, Object> map) {
		return this.selectList("getIndexResumeListCounts", map);
	}

	@Override
	public int insertViewLog(Map<String, Object> map) {
		return this.insert("insertViewLog", map);
	}

	@Override
	public List<Map<String, Object>> getFaceTimes(Map<String, Object> map) {
		return this.selectList("getFaceTimes", map);
	}

	@Override
	public List<Map<String, Object>> checkResumeEmail(Map<String, Object> map) {
		return this.selectList("checkResumeEmail", map);
	}

	@Override
	public List<Map<String, Object>> getCurrentStatus(Map<String, Object> map) {
		return this.selectList("getCurrentStatus", map);
	}

	@Override
	public List<Map<String, Object>> getLogList(Map<String, Object> map) {
		return this.selectList("getLogList", map);
	}

}
