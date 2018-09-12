package com.kaipin.university.repository.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.SchoolUserInfo;
import com.kaipin.university.repository.dao.user.ISchoolUserInfoDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class SchoolUserInfoDaoImpl extends MybatisBaseDAO<SchoolUserInfo,String> implements ISchoolUserInfoDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.user.ISchoolUserInfoDao";
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(SchoolUserInfo record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public SchoolUserInfo selectByPrimaryKey(String id) {
		return (SchoolUserInfo) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolUserInfo record) {
		return update("updateByPrimaryKeySelective", record);
	}

}
