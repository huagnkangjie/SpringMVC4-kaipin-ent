package com.kaipin.oss.repository.dao.stu.impl;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.stu.StuUser;
import com.kaipin.oss.repository.dao.stu.IStuUserDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class StuUserDaoImpl extends MybatisBaseDAO<StuUser, String> implements IStuUserDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.oss.repository.dao.stu.IStuUserDao";
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
	public int insertSelective(StuUser record) {
		this.insert("insertSelective", record);
		return 0;
	}

	@Override
	public StuUser selectByPrimaryKey(String id) {
		return (StuUser) this.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(StuUser record) {
		
		try {
			this.update("updateByPrimaryKeySelective", record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
