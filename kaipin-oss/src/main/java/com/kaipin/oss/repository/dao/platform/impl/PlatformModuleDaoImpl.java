package com.kaipin.oss.repository.dao.platform.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.model.platform.PlatformModule;

import com.kaipin.oss.repository.dao.platform.PlatformModuleDao;
import com.kaipin.oss.repository.mybatis.dao.IBasePageSql;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class PlatformModuleDaoImpl extends MybatisBaseDAO<PlatformModule, Integer>implements PlatformModuleDao {

	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.mapper.platform.PlatformModule";

	@Override
	public String getDefaultSqlNamespace() {

		return NAMESPACE_INFO;
	}

	@Override
	public List<PlatformModule> getList() {
		return this.findListBy("getList");
	}

	@Override
	public List<PlatformModule> getListNotRoot() {
	 
		return this.findListBy("getListNotRoot");
	}

	@Override
	public IGenericPage<PlatformModule> getListByParentId(Map<String, Object> param, int pageNo, int pageSize) {
		return this.findPageBy( new IBasePageSql(){

			@Override
			public String getPageListSqlName() {
				return "getListByParentId";
			}

			@Override
			public String getPageCountSqlName() {
			 
				return "getListByParentIdCount";
			}}, param, pageNo, pageSize, null, null);
	}

	@Override
	public boolean insertModule(PlatformModule function) {
		try {
			this.save(function);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delModule(String id) {
		try {
			this.delete("delModule", id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
