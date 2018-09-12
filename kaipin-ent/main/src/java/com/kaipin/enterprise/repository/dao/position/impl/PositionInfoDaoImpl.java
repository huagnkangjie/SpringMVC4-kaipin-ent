package com.kaipin.enterprise.repository.dao.position.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.position.PositionInfo;
import com.kaipin.enterprise.repository.dao.position.IPositionInfoDao;
import com.kaipin.university.model.user.SchoolInfoLink;

@Repository
public class PositionInfoDaoImpl extends MybatisBaseDAO<PositionInfo,String> implements IPositionInfoDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.position.IPositionInfoDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(PositionInfo record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public PositionInfo selectByPrimaryKey(String id) {
		return (PositionInfo) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(PositionInfo record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PositionInfo> selectAll(Map<String, Object> map) {
		return this.selectList("selectAll", map);
	}

	@Override
	public Integer selectAllTotal(Map<String, Object> map) {
		return (Integer) this.selectOne("selectAllTotal",map);
	}

	@Override
	public int updateStatus(Map<String, Object> map) {
		return this.update("updateStatus", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCount(Map<String, Object> map) {
		return this.selectList("getCount", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> search(Map<String, Object> map) {
		return this.selectList("search", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> countPostionByEndtime(Map<String, Object> map) {
		return this.selectList("countPostionByEndtime", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> datagridIndex(Map<String, Object> map) {
		return this.selectList("datagridIndex", map);
	}

}
