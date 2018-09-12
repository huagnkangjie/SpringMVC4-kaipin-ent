package com.enterprise.service.common;

import com.enterprise.model.common.CommonAnnex;

public interface ICommonAnnexService {
	public int deleteByPrimaryKey(String id);

	public int insertSelective(CommonAnnex record);

	public CommonAnnex selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(CommonAnnex record);

}
