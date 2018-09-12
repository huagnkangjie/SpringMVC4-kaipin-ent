package com.kaipin.oss.repository.dao.position.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.position.PositionInfo;
import com.kaipin.oss.repository.dao.position.PositionInfoDao;
import com.kaipin.oss.repository.mybatis.dao.IBasePageSql;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class PositionInfoDaoImpl extends MybatisBaseDAO<PositionInfo, String> implements PositionInfoDao{

	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.mapper.position.PositionInfo";
	
	@Override
	public String getDefaultSqlNamespace() {
		return NAMESPACE_INFO;
	}

	@Override
	public IGenericPage<PositionInfo> getPositionList(Map<String, Object> param, int pageNo,int pageSize) {
		return this.findPageBy(new IBasePageSql() {
			
			@Override
			public String getPageListSqlName() {
				return "getPositionList";
			}
			
			@Override
			public String getPageCountSqlName() {
				return "getPositionListCount";
			}
		}, param, pageNo, pageSize, null, null);
	}

	@Override
	public PositionInfo selectByPrimaryKey(String id) {
		return (PositionInfo) this.selectOne("selectByPrimaryKey",id);
	}

}
