package com.kaipin.common.repository.mybatis.dao;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO.IBasePageSql;

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
