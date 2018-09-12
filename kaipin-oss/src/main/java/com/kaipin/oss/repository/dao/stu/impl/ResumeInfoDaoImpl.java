package com.kaipin.oss.repository.dao.stu.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.stu.resume.ResumeInfo;
import com.kaipin.oss.repository.dao.stu.IResumeInfoDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;


@Repository
public class ResumeInfoDaoImpl extends MybatisBaseDAO<ResumeInfo, String> implements IResumeInfoDao {

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.oss.repository.dao.stu.IResumeInfoDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(ResumeInfo record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public ResumeInfo selectByPrimaryKey(String id) {
		return (ResumeInfo) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(ResumeInfo record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@Override
	public ResumeInfo selectByUid(String uId) {
		return (ResumeInfo) this.selectOne("selectByUid", uId);
	}
}
