package com.kaipin.oss.service.sch;

import java.util.List;

import com.kaipin.oss.model.sch.SchInfoFeedback;

public interface ISchInfoFeedbackService {
	/**
	 * 用户资质审核反馈信息
	 * @param function
	 * @return
	 */
	public boolean insertFeedback(SchInfoFeedback function);
	
	/**
	 * 用户资质审核反馈信息
	 * @param function
	 * @return
	 */
	public boolean insertFeedbackRelation(SchInfoFeedback function);
	
	/**
	 * 获取审核配置
	 * @return
	 */
	public List<SchInfoFeedback> getClassConfig();
	
	/**
	 * 获取审核模板
	 * @return
	 */
	public List<SchInfoFeedback> getClassTemplate(String configId);
}
