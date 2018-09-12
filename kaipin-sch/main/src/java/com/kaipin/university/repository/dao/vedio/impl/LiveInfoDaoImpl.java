package com.kaipin.university.repository.dao.vedio.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.university.model.vedio.LiveInfo;
import com.kaipin.university.repository.dao.vedio.ILiveInfoDao;

@Repository
public class LiveInfoDaoImpl extends MybatisBaseDAO<LiveInfo,String> implements ILiveInfoDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.vedio.ILiveInfoDao";
	}
	
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(LiveInfo record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public LiveInfo selectByPrimaryKey(String id) {
		try {
			return (LiveInfo) this.selectOne("selectByPrimaryKey", id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(LiveInfo record) {
		return this.update("updateByPrimaryKeySelective", record);
	}
	

	@Override
	public int insert(LiveInfo record) {
		return this.insert("insert", record);
	}

	@Override
	public int insertRoom(Map<String, Object> map) {
		return this.insert("insertRoom", map);
	}

	@Override
	public int insertVedio(Map<String, Object> map) {
		return this.insert("insertVedio", map);
	}

	@Override
	public int updateVedio(Map<String, Object> map) {
		return this.update("updateVedio", map);
	}

	@Override
	public int updateByPrimaryKey(LiveInfo record) {
		return this.update("updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiveInfo> getList(Map<String, Object> map) {
		return this.selectList("getList", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getListCounts(Map<String, Object> map) {
		return this.selectList("getListCounts", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getMap(Map<String, Object> map) {
		List<Map<String, Object>> list = this.selectList("getMap", map);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
