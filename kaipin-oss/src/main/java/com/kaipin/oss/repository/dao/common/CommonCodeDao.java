package com.kaipin.oss.repository.dao.common;

import java.util.List;

import com.kaipin.oss.model.common.Code;
import com.kaipin.oss.model.common.CommCardType;
import com.kaipin.oss.model.common.CommCompanyType;
import com.kaipin.oss.model.common.CommEducation;
import com.kaipin.oss.model.common.CommGlobalGroup;
import com.kaipin.oss.model.common.CommJobType;
import com.kaipin.oss.model.common.CommLanguage;
import com.kaipin.oss.model.common.CommLocation;
import com.kaipin.oss.model.common.CommMajor;
import com.kaipin.oss.model.common.CommSalary;
import com.kaipin.oss.model.common.CommWorkExperience;

public interface CommonCodeDao {
	
	/**
	 * 根据code 获取对象
	 * @param code
	 * @return
	 */
	public CommLocation getLocationByCode(String code);
	
	
	/**
	 * 根据parentCode 获取list
	 * @param parentCode
	 * @return
	 */
	public List<Code> getLocationByParentCode(String parentCode);
	
	
	/**
	 * 根据code 行业
	 * @param code
	 * @return
	 */
	public Code getCommIndustry(String code);
	
	
	
	public CommEducation getEducation(String code);
	
	public CommCardType getCardType(String code);
	
	public CommCompanyType getCompanyType(String code);
	
	public CommGlobalGroup getGlobalGroup(String code);
	
	public CommLanguage getLanguage(String code);
	
	public CommMajor getMajor(String code);
	
	public CommSalary getSalary(String code);
	
	public CommWorkExperience getWorkExperience(String code);
	
	public CommJobType getJobType(String code);

}
