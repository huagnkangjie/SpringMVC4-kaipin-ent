package com.kaipin.oss.service.stu.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.model.common.UserLocalauth;
import com.kaipin.oss.repository.dao.common.IUserLocalAuthDao;
import com.kaipin.oss.service.stu.IUserLocalauthService;

@Service("userLocalauthService")
public class UserLocalauthServiceImpl implements IUserLocalauthService{

	@Autowired
	private IUserLocalAuthDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			dao.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(UserLocalauth record) {
		try {
			dao.insertSelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public UserLocalauth selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLocalauth record) {
		try {
			System.out.println(">>>>>>>>>>>>>>>>>> "+record.getEnable());
			dao.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public UserLocalauth selectByOrgId(String orgId) {
		return dao.selectByOrgId(orgId);
	}

}
