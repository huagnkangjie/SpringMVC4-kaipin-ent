package com.kaipin.oss.repository.mybatis.dao;

public class PageSqlAdapter implements  IBasePageSql{

	@Override
	public String getPageListSqlName() {
 
		return MybatisBaseDAO.SQL_FINDPAGEBY;
	}

	@Override
	public String getPageCountSqlName() {
		return MybatisBaseDAO.SQL_GETCOUNTBY;
	}

}
