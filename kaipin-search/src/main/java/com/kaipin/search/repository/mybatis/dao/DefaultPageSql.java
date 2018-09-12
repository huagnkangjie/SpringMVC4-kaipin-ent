package com.kaipin.search.repository.mybatis.dao;

import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO.IBasePageSql;

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
