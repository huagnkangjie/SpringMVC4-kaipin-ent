package com.kaipin.enterprise.service.msg;

import com.kaipin.enterprise.model.msg.MsgUserOffer;

public interface IMsgUserOfferService {

	int deleteByPrimaryKey(String id);

    int insertSelective(MsgUserOffer record);

    MsgUserOffer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserOffer record);

}
