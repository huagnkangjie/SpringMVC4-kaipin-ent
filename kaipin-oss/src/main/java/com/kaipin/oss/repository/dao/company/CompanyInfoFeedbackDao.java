package com.kaipin.oss.repository.dao.company;

import java.util.List;

import com.kaipin.oss.model.company.CompanyInfoFeedback;

public interface CompanyInfoFeedbackDao {

	/**
	 * 用户资质审核反馈信息
	 * @param function
	 * @return
	 */
	public boolean insertFeedback(CompanyInfoFeedback function);
	
	/**
	 * 用户资质审核反馈信息
	 * @param function
	 * @return
	 */
	public boolean insertFeedbackRelation(CompanyInfoFeedback function);
	
	/**
	 * 获取审核配置
	 * @return
	 */
	public List<CompanyInfoFeedback> getClassConfig();
	
	/**
	 * 获取审核模板
	 * @return
	 */
	public List<CompanyInfoFeedback> getClassTemplate(String configId);
}
