package com.kaipin.university.service.user;

import java.util.List;
import java.util.Map;

import com.kaipin.university.model.user.SchoolInfoLink;

public interface ISchoolInfoLinkService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(SchoolInfoLink record);

	public SchoolInfoLink selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(SchoolInfoLink record);
	
	public String getLinkDetail(String id);
	
	public List<Map<String,Object>> getLinkDetailList(String id);
    
	public int updateDetail(Map<String,Object> map);
    
	public int insertDetail(Map<String,Object> map);
}
