package com.kaipin.enterprise.repository.dao.msg;

import com.kaipin.enterprise.model.msg.MsgUserOffer;

public interface IMsgUserOfferDao {

	int deleteByPrimaryKey(String id);

    int insertSelective(MsgUserOffer record);

    MsgUserOffer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserOffer record);

}
