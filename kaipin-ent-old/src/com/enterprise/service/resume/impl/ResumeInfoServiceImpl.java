package com.enterprise.service.resume.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.resume.ResumeInfoMapper;
import com.enterprise.model.resume.ResumeInfo;
import com.enterprise.service.resume.IResumeInfoService;

@Service("resumeInfoService")
@Repository
public class ResumeInfoServiceImpl implements IResumeInfoService{
	@Autowired
	private ResumeInfoMapper mapper;


	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(ResumeInfo record) {
		try {
			mapper.insert(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public ResumeInfo selectByPrimaryKey(String id) {
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int updateByPrimaryKeySelective(ResumeInfo record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
}
