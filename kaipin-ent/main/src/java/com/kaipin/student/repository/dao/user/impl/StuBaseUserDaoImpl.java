package com.kaipin.student.repository.dao.user.impl;

import java.util.List;
import java.util.Map;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.student.model.user.StuUser;
import com.kaipin.student.repository.dao.user.IStuBaseUserDao;

public class StuBaseUserDaoImpl extends MybatisBaseDAO<StuUser, String> implements IStuBaseUserDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.student.repository.dao.user.IStuBaseUserDao";
	}
	
	@Override
	public int insertDoc(Map<String, Object> map) {
		try {
			this.insert("insertDoc", map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectDocList(Map<String, Object> map) {
		return this.selectList("selectDocList", map);
	}

	@Override
	public int deleteDoc(Map<String, Object> map) {
		try {
			this.delete("deleteDoc", map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}


}
