package com.kaipin.oss.repository.dao.stu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.stu.StuBaseInfo;
import com.kaipin.oss.repository.dao.stu.IStuBaseInfoDao;
import com.kaipin.oss.repository.mybatis.dao.IBasePageSql;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class IStuBaseInfoImpl extends MybatisBaseDAO<StuBaseInfo, String> implements IStuBaseInfoDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.oss.repository.dao.stu.IStuBaseInfoDao";
	}

	@Override
	public IGenericPage<StuBaseInfo> getStuList(Map<String, Object> param, int pageNo, int pageSize) {
		IBasePageSql sql = new IBasePageSql() {
			
			@Override
			public String getPageListSqlName() {
				return "getStuList";
			}
			
			@Override
			public String getPageCountSqlName() {
				return "getStuListCount";
			}
		};
		return this.findPageBy(sql, param, pageNo, pageSize, "b.create_time", "desc");
	}

	@Override
	public IGenericPage<StuBaseInfo> getStuVipList(Map<String, Object> param, int pageNo, int pageSize) {
		IBasePageSql sql = new IBasePageSql() {
			
			@Override
			public String getPageListSqlName() {
				return "getStuVipList";
			}
			
			@Override
			public String getPageCountSqlName() {
				return "getStuVipListCount";
			}
		};
		return this.findPageBy(sql, param, pageNo, pageSize, "b.create_time", "desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getLikePositionList(Map<String, Object> param) {
		return this.selectList("getLikePositionList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getOneToOneCount(String uid) {
		List<Map<String,Object>> list = this.selectList("getOneToOneCount", uid);
		long count = 0;
		if(list.size() > 0){
			count = Long.valueOf(list.get(0).get("counts") + "");
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getOneToOnePositionCount(String uid) {
		List<Map<String,Object>> list = this.selectList("getOneToOnePositionCount", uid);
		long count = 0;
		if(list.size() > 0){
			count = Long.valueOf(list.get(0).get("counts") + "");
		}
		return count;
	}

	@Override
	public boolean insertVipRecodGroup(Map<String, Object> map) {
		try {
			this.insert("insertVipRecodGroup", map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean insertVipRecod(Map<String, Object> map) {
		try {
			this.insert("insertVipRecod", map);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
