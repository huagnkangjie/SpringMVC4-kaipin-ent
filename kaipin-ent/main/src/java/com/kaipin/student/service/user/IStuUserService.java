package com.kaipin.student.service.user;

import com.kaipin.student.model.user.StuUser;

public interface IStuUserService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(StuUser record);

	public StuUser selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(StuUser record);
}
