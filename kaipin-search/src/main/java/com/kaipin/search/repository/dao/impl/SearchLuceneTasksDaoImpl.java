package com.kaipin.search.repository.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.search.common.page.IGenericPage;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.repository.dao.SearchLuceneTasksDao;
import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO.IBasePageSql;

@Repository
public class SearchLuceneTasksDaoImpl extends MybatisBaseDAO<SearchLuceneTasks, String>implements SearchLuceneTasksDao {

	@Override
	public int deleteByPrimaryKey(String id) {

		return 0;
	}

	@Override
	public SearchLuceneTasks selectByPrimaryKey(String id) {

		return null;
	}

	@Override
	public IGenericPage<SearchLuceneTasks> selectUnHandle(int pageNo, int pageSize) {

		IBasePageSql sql = new IBasePageSql() {

			@Override
			public String getPageListSqlName() {

				return "selectUnHandle";
			}

			@Override
			public String getPageCountSqlName() {

				return "selectUnHandleCount";
			}
		};

		return findPageBy(sql, null, pageNo, pageSize, null, null);
	}

	@Override
	public int updateByPrimaryKeySelective(SearchLuceneTasks record) {

		return 0;
	}

	@Override
	public String getDefaultSqlNamespace() {

		return "SearchLuceneTasksMapper";
	}

	@Override
	public int updateBatchHandle(List<String> list) {

		return this.update("updateBatchHandle", list);

	}

	@Override
	public int updateHandled(String id) {
		return this.update("updateHandled", id);

	}

	@Override
	public int insertLuceneTask(Map<String, Object> map) {
		return this.insert("insertLuceneTask", map);
	}

}
