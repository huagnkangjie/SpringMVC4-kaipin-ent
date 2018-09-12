package com.kaipin.university.repository.dao.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.repository.dao.user.ISchoolInfoLinkDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.common.util.StringUtil;

@Repository
public class SchoolInfoLinkDaoImpl extends MybatisBaseDAO<SchoolInfoLink,String> implements ISchoolInfoLinkDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.user.ISchoolInfoLinkDao";
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insertSelective(SchoolInfoLink record) {
		return this.insert("insertSelective", record);
	}

	@Override
	public SchoolInfoLink selectByPrimaryKey(String id) {
		return (SchoolInfoLink) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolInfoLink record) {
		return update("updateByPrimaryKeySelective", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLinkDetail(String id) {
		List<Map<String, Object>> list =this.selectList("getLinkDetail", id);
		String detail = "";
		if(list.size() > 0){
			String t = list.get(0).get("detail") + "";
			if(StringUtil.isNotEmpty(t)){
				detail = t;
			}
		}
 		return detail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getLinkDetailList(String id) {
		return this.selectList("getLinkDetailList", id);
	}

	@Override
	public int updateDetail(Map<String, Object> map) {
		try {
			this.update("updateDetail", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertDetail(Map<String, Object> map) {
		try {
			this.insert("insertDetail", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


}
