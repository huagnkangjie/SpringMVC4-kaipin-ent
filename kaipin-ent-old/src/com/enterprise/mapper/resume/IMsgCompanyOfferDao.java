package com.enterprise.mapper.resume;

import java.util.List;

import com.enterprise.model.resume.MsgCompanyOffer;


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