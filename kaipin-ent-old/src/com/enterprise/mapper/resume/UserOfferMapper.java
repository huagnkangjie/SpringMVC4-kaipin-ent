package com.enterprise.mapper.resume;

import com.enterprise.model.UserOffer;

public interface UserOfferMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserOffer record);

    int insertSelective(UserOffer record);

    UserOffer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserOffer record);

    int updateByPrimaryKey(UserOffer record);
}