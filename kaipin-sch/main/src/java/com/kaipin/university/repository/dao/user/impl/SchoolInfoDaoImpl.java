package com.kaipin.university.repository.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.user.SchoolInfo;
import com.kaipin.university.repository.dao.user.ISchoolInfoDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class SchoolInfoDaoImpl extends MybatisBaseDAO<SchoolInfo,String> implements ISchoolInfoDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.user.ISchoolInfoDao";
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(SchoolInfo record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public SchoolInfo selectByPrimaryKey(String id) {
		return (SchoolInfo) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolInfo record) {
		return update("updateByPrimaryKeySelective", record);
	}


}
