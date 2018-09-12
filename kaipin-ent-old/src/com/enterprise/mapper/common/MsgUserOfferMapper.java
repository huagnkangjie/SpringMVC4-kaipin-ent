package com.enterprise.mapper.common;

import com.enterprise.model.common.MsgUserOffer;


public interface MsgUserOfferMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgUserOffer record);

    int insertSelective(MsgUserOffer record);

    MsgUserOffer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserOffer record);

    int updateByPrimaryKey(MsgUserOffer record);
}