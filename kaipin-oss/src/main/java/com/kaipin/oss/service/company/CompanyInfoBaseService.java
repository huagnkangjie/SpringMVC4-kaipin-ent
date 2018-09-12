package com.kaipin.oss.service.company;

import java.util.List;
import java.util.Map;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.company.CompanyDocument;
import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.model.platform.PlatformUser;

public interface CompanyInfoBaseService {

	/**
	 * 获取企业list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public IGenericPage getPage(Map<String,Object> param, int pageNo,int pageSize);
	
	/**
	 * 获取企业资质认证列表
	 * @param function
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public IGenericPage<CompanyInfoCount> getCompanyDocList(Map<String,Object> param, int pageNo, int pageSize);
	
	/**
	 * 获取资质认证
	 * @param companyId
	 * @return
	 */
	public List<CompanyDocument> getComanyDoc(String companyId);
	
	/**
	 * 获取企业最近一月注册统计
	 * @return
	 */
	public List<Map<String, Object>> getRegeditCompanyCount(Map<String, Object> map);
	
	/**
	 * 获取学生最近一月注册统计
	 * @return
	 */
	public List<Map<String, Object>> getRegeditStudentCount(Map<String, Object> map);
	
	/**
	 * 获取学校最近一月注册统计
	 * @return
	 */
	public List<Map<String, Object>> getRegeditSchCount(Map<String, Object> map);
	
	/**
	 * 获取企业的总注册量
	 * @return
	 */
	public List<Map<String, Object>> getRegeditAllCompanyCount();
	
	/**
	 * 获取学生的总注册量
	 * @return
	 */
	public List<Map<String, Object>> getRegeditAllStudentCount();
	
	/**
	 * 获取职位总数量
	 * @return
	 */
	public List<Map<String, Object>> getPositionCount();
	
	/**
	 * 获取简历总数量
	 * @return
	 */
	public List<Map<String, Object>> getResumeCount();
	
	/**
	 * 被投递的职位数
	 * @return
	 */
	public List<Map<String, Object>> getPositionDeliveryCount();
	
	/**
	 * 投递了简历的简历数
	 * @return
	 */
	public List<Map<String, Object>> getResumeDeliveryCount();
	
	/**
	 * 统计宣讲会各个类型的数量
	 * @return
	 */
	public List<Map<String, Object>> getXJHCount(String companyId);
	
	/**
	 * 统计offer发送量
	 * @return
	 */
	public List<Map<String, Object>> getOfferCount(String companyId);
	
	/**
	 * 判断企业是否完成邮箱配置
	 * @param companyId
	 * @return
	 */
	public List<Map<String, Object>> checkOfferConfig(String companyId);
}
