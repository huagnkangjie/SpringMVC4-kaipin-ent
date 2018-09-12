package com.enterprise.service.resume;

import java.util.List;

import com.enterprise.model.resume.MsgCompanyOffer;


public interface IMsgCompanyOfferService {
	
	public int deleteByPrimaryKey(String id);

	public int insertSelective(MsgCompanyOffer record);

	public MsgCompanyOffer selectByPrimaryKey(String id);

	public int updateByPrimaryKey(MsgCompanyOffer record);
    
    /**
     * 获取需要发送邮件的列表
     * 每分钟抽取5个
     * @return
     */
	public List<MsgCompanyOffer> getNeedSendOfferList();

}
