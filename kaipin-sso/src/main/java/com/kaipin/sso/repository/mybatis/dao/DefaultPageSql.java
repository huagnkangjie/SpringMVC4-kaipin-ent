package com.kaipin.sso.repository.mybatis.dao;

public class DefaultPageSql implements  IBasePageSql{

	@Override
	public String getPageListSqlName() {
 
		return MybatisBaseDAO.SQL_FINDPAGEBY;
	}

	@Override
	public String getPageCountSqlName() {
		return MybatisBaseDAO.SQL_GETCOUNTBY;
	}

}
