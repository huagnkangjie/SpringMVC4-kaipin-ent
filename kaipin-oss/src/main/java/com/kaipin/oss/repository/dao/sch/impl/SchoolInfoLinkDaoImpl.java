package com.kaipin.oss.repository.dao.sch.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.sch.SchoolInfoLink;
import com.kaipin.oss.repository.dao.sch.ISchoolInfoLinkDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class SchoolInfoLinkDaoImpl extends MybatisBaseDAO<SchoolInfoLink, String> implements ISchoolInfoLinkDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.oss.repository.dao.sch.ISchoolInfoLinkDao";
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			this.delete("deleteByPrimaryKey", id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(SchoolInfoLink record) {
		this.insert("insertSelective", record);
		return 0;
	}

	@Override
	public SchoolInfoLink selectByPrimaryKey(String id) {
		
		return (SchoolInfoLink) this.selectList("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolInfoLink record) {
		
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
