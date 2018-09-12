package com.kaipin.student.service.resume;

import com.kaipin.student.model.resume.UserOffer;

public interface IUserOfferService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(UserOffer record);

    public UserOffer selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(UserOffer record);
}
