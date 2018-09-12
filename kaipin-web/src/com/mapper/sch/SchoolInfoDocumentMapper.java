package com.mapper.sch;

import com.model.sch.SchoolInfoDocument;

public interface SchoolInfoDocumentMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolInfoDocument record);

    int insertSelective(SchoolInfoDocument record);

    SchoolInfoDocument selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolInfoDocument record);

    int updateByPrimaryKey(SchoolInfoDocument record);
}