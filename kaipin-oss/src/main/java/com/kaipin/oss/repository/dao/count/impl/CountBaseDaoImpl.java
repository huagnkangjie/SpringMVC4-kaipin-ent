package com.kaipin.oss.repository.dao.count.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.count.CountBase;
import com.kaipin.oss.repository.dao.count.ICountBaseDao;
import com.kaipin.oss.repository.mybatis.dao.IBasePageSql;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class CountBaseDaoImpl extends MybatisBaseDAO<CountBase, String> implements ICountBaseDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.oss.repository.dao.count.ICountBaseDao";
	}

	@Override
	public IGenericPage<CountBase> getDeliveryPositionList(Map<String, Object> param, int pageNo, int pageSize) {
		IBasePageSql sql = new IBasePageSql() {
			
			@Override
			public String getPageListSqlName() {
				return "getDeliveryPositionList";
			}
			
			@Override
			public String getPageCountSqlName() {
				return "getDeliveryPositionListCount";
			}
		};
		return this.findPageBy(sql, param, pageNo, pageSize, "a.create_time", "desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getWeekCount(Map<String, Object> map) {
		return this.selectList("getWeekCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getMonthCount(Map<String, Object> map) {
		return this.selectList("getMonthCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getQuarterCount(Map<String, Object> map) {
		return this.selectList("getQuarterCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getYearCount(Map<String, Object> map) {
		return this.selectList("getYearCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getTimeToTimeCount(Map<String, Object> map) {
		return this.selectList("getTimeToTimeCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getUserCount(Map<String, Object> map) {
		return this.selectList("getUserCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getMonthActiveCount(Map<String, Object> map) {
		return this.selectList("getMonthActiveCount", map);
	}

}
