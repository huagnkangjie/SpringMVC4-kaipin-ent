package com.kaipin.enterprise.repository.dao.msg;

import java.util.List;

import com.kaipin.enterprise.model.msg.MsgCompanyOffer;

public interface IMsgCompanyOfferDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(MsgCompanyOffer record);

    MsgCompanyOffer selectByPrimaryKey(String id);

    int updateByPrimaryKey(MsgCompanyOffer record);
    
    /**
     * 获取需要发送邮件的列表
     * 每分钟抽取5个
     * @return
     */
    List<MsgCompanyOffer> getNeedSendOfferList();
}