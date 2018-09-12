package com.kaipin.oss.repository.dao.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.model.common.CommIndustry;
import com.kaipin.oss.model.common.CommJobType;
import com.kaipin.oss.model.common.CommLanguage;
import com.kaipin.oss.model.common.CommLocation;
import com.kaipin.oss.model.common.CommMajor;
import com.kaipin.oss.model.common.CommSalary;
import com.kaipin.oss.model.common.CommWorkExperience;
import com.kaipin.oss.model.common.Code;
import com.kaipin.oss.model.common.CommCardType;
import com.kaipin.oss.model.common.CommCompanyType;
import com.kaipin.oss.model.common.CommEducation;
import com.kaipin.oss.model.common.CommGlobalGroup;
import com.kaipin.oss.repository.dao.common.CommonCodeDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;


@Repository
public class CommonCodeDaoImpl extends MybatisBaseDAO<Code, String>implements CommonCodeDao{

	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.mapper.common.CommonCode";
	
	@Override
	public String getDefaultSqlNamespace() {
		return NAMESPACE_INFO;
	}
	
	@Override
	public CommLocation getLocationByCode(String code) {
		return (CommLocation) this.selectOne("getLocationByCode", code);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Code> getLocationByParentCode(String parentCode) {
		return this.selectList("getLocationByParentCode", parentCode);
	}

	@Override
	public Code getCommIndustry(String code) {
		Code comm = new Code();
		comm.setCommIndustry((CommIndustry) this.selectOne("getCommIndustry", code));
		return comm;
	}

	@Override
	public CommEducation getEducation(String code) {
		return (CommEducation) this.selectOne("getEducation", code);
	}

	@Override
	public CommCardType getCardType(String code) {
		return (CommCardType) this.selectOne("getCardType", code);
	}

	@Override
	public CommCompanyType getCompanyType(String code) {
		return (CommCompanyType) this.selectOne("getCompanyType", code);
	}

	@Override
	public CommGlobalGroup getGlobalGroup(String code) {
		return (CommGlobalGroup) this.selectOne("getGlobalGroup", code);
	}

	@Override
	public CommLanguage getLanguage(String code) {
		return (CommLanguage) this.selectOne("getLanguage", code);
	}

	@Override
	public CommMajor getMajor(String code) {
		return (CommMajor) this.selectOne("getMajor", code);
	}

	@Override
	public CommSalary getSalary(String code) {
		return (CommSalary) this.selectOne("getSalary", code);
	}

	@Override
	public CommWorkExperience getWorkExperience(String code) {
		return (CommWorkExperience) this.selectOne("getWorkExperience", code);
	}

	@Override
	public CommJobType getJobType(String code) {
		return (CommJobType) this.selectOne("getJobType", code);
	}

	

}
