package com.kaipin.student.service.resume.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.model.resume.ResumeInfo;
import com.kaipin.student.repository.dao.resume.IResumeInfoDao;
import com.kaipin.student.service.resume.IResumeInfoService;

@Service("resumeInfoService")
public class ResumeInfoServiceImpl implements IResumeInfoService{
	
	@Autowired
	private IResumeInfoDao dao;

	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ResumeInfo record) {
		return dao.insertSelective(record);
	}

	@Override
	public ResumeInfo selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ResumeInfo record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public ResumeInfo selectByUid(String uId) {
		return dao.selectByUid(uId);
	}

}
