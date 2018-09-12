package com.enterprise.mapper.common;

import com.enterprise.model.common.CommonAnnex;

public interface CommonAnnexMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommonAnnex record);

    int insertSelective(CommonAnnex record);

    CommonAnnex selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommonAnnex record);

    int updateByPrimaryKey(CommonAnnex record);
}