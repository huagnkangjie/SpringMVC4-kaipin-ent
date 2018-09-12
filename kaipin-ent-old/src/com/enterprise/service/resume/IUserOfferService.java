package com.enterprise.service.resume;

import com.enterprise.model.UserOffer;

public interface IUserOfferService {

	public int deleteByPrimaryKey(String id);


	public int insertSelective(UserOffer record);

	public UserOffer selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(UserOffer record);

}
