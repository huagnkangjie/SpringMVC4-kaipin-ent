package com.kaipin.common.repository.dao.followfans.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.dao.followfans.ICommFollowFansDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.university.model.feed.Feed;

@Repository
public class CommFollowFansDaoImpl extends MybatisBaseDAO<Feed, String> implements ICommFollowFansDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.common.repository.dao.followfans.ICommFollowFansDao";
	}
	
	@Override
	public boolean insertFollowCount(String org_id) {
		try {
			this.insert("insertFollowCount", org_id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean updateFollowCount(Map<String, Object> map) {
		try {
			this.update("updateFollowCount", map);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getFollowFanCounts(String org_id) {
		return this.selectList("getFollowFanCounts", org_id);
	}

	

}
