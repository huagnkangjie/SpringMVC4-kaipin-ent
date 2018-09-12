package com.enterprise.service.common;

import com.enterprise.model.common.MsgUserOffer;

public interface IMsgUserOfferService {

	int deleteByPrimaryKey(String id);


    int insertSelective(MsgUserOffer record);

    MsgUserOffer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserOffer record);

}
