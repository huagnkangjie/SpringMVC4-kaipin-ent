package com.service.sch;

import com.model.sch.SchoolInfoLink;

public interface ISchoolInfoLinkService {
	
	public int deleteByPrimaryKey(String id);

	public int insertSelective(SchoolInfoLink record);

	public SchoolInfoLink selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(SchoolInfoLink record);



}
