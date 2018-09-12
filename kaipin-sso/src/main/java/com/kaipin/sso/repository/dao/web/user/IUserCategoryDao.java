package com.kaipin.sso.repository.dao.web.user;

import com.kaipin.sso.entity.web.user.UserCategory;

/**
 * 用户分类
 * 
 *
 */
public interface IUserCategoryDao {

/***
 * 根据id查询
 * @param categoryId 用户分类id
 * @return
 */
	public UserCategory getById (String categoryId);
	
	
}
