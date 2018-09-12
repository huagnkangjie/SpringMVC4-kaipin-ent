package com.kaipin.task.repository.dao.web.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.task.repository.dao.web.common.ICommonDao;
import com.kaipin.task.repository.mybatis.dao.MybatisBaseDAO;

@Repository
@SuppressWarnings("unchecked")
public class CommonDaoImpl extends MybatisBaseDAO<Object, String> implements ICommonDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.task.repository.dao.web.common.ICommonDao";
	}

	@Override
	public Map<String, Object> getCompanyInfo(String companyId) {
		List<Map<String,Object>> list = this.selectList("getCompanyInfo", companyId);
		Map<String,Object> map = new HashMap<>();
		if(list.size() > 0){
			map = list.get(0);
		}
		return map;
	}

	@Override
	public Map<String, Object> getSchoolInfo(String schId) {
		List<Map<String,Object>> list = this.selectList("getSchoolInfo", schId);
		Map<String,Object> map = new HashMap<>();
		if(list.size() > 0){
			map = list.get(0);
		}
		return map;
	}

	@Override
	public Map<String, Object> getStudentInfo(String stuId) {
		List<Map<String,Object>> list = this.selectList("getStudentInfo", stuId);
		Map<String,Object> map = new HashMap<>();
		if(list.size() > 0){
			map = list.get(0);
		}
		return map;
	}

}
