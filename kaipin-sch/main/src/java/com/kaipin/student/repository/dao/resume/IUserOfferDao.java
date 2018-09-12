package com.kaipin.student.repository.dao.resume;

import com.kaipin.student.model.resume.UserOffer;

public interface IUserOfferDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(UserOffer record);

    UserOffer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserOffer record);
}