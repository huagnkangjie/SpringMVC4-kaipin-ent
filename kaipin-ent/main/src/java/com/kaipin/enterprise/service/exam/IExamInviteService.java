package com.kaipin.enterprise.service.exam;

import com.kaipin.enterprise.model.exam.ExamInvite;

public interface IExamInviteService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(ExamInvite record);

	public ExamInvite selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(ExamInvite record);
}