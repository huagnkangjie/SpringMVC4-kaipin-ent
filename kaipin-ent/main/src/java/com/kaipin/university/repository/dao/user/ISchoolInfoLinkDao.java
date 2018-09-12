package com.kaipin.university.repository.dao.user;

import java.util.List;
import java.util.Map;

import com.kaipin.university.model.user.SchoolInfoLink;

public interface ISchoolInfoLinkDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(SchoolInfoLink record);

    SchoolInfoLink selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolInfoLink record);
    
    String getLinkDetail(String id);
    
    List<Map<String,Object>> getLinkDetailList(String id);
    
    int updateDetail(Map<String,Object> map);
    
    int insertDetail(Map<String,Object> map);

}