package com.kaipin.oss.repository.dao.sch.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.sch.SchBaseInfo;
import com.kaipin.oss.repository.dao.sch.ISchBaseInfoDao;
import com.kaipin.oss.repository.mybatis.dao.IBasePageSql;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class SchBaseInfoDaoImpl extends MybatisBaseDAO<SchBaseInfo, String> implements ISchBaseInfoDao{


	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.oss.repository.dao.sch.ISchBaseInfoDao";
	}

	@Override
	public IGenericPage<SchBaseInfo> getSchDocList(Map<String, Object> param, int pageNo, int pageSize) {
		IBasePageSql sql = new IBasePageSql() {
			
			@Override
			public String getPageListSqlName() {
				return "getSchDocumentList";
			}
			
			@Override
			public String getPageCountSqlName() {
				return "getSchDocumentCount";
			}
		};
		return this.findPageBy(sql, param, pageNo, pageSize, "t3.create_time", "desc");
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getSchDocList(String schUserId) {
		return this.selectList("getSchDocList", schUserId);
	}
}
