package com.kaipin.oss.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.kaipin.oss.repository.dao.common.CommonCodeDao;
import com.kaipin.oss.service.common.CommonCodeService;
import com.kaipin.oss.util.StringUtil;

@Service
public class CommonCodeServiceImpl implements CommonCodeService{

	@Autowired
	private CommonCodeDao dao;
	
	@Override
	public CommLocation getLocationByCode(String code) {
		return dao.getLocationByCode(code);
	}

	@Override
	public List<Code> getLocationByParentCode(String parentCode) {
		return dao.getLocationByParentCode(parentCode);
	}

	@Override
	public Code getCommIndustry(String code) {
		return dao.getCommIndustry(code);
	}

	@Override
	public String getLocationByCodeService(String code) {
		String area1 = "";//省
		String area2 = "";//市
		if(StringUtil.isNotEmpty(code)){
			if(code.equals("530") || code.equals("538") || code.equals("551") || code.equals("531") ||
					code.equals("561") || code.equals("562") || code.equals("563")){
				switch(code){
					case "530" : area1 = "北京"; break;
					case "538" : area1 = "上海"; break;
					case "551" : area1 = "重庆"; break;
					case "531" : area1 = "天津"; break;
					case "561" : area1 = "香港"; break;
					case "562" : area1 = "澳门"; break;
					case "563" : area1 = "台湾"; break;
				}
			}else{
				CommLocation common = dao.getLocationByCode(code);
				if(common != null){
					area2 = common.getLocationName();
					String parentCode = common.getParentCode();
					CommLocation commonParent = dao.getLocationByCode(parentCode);
					if(commonParent != null){
						area1 = commonParent.getLocationName();
					}
				}	
			}
			
		}
		return area1 + " " + area2;
	}

	@Override
	public CommEducation getEducation(String code) {
		return dao.getEducation(code);
	}

	@Override
	public CommCardType getCardType(String code) {
		return dao.getCardType(code);
	}

	@Override
	public CommCompanyType getCompanyType(String code) {
		return dao.getCompanyType(code);
	}

	@Override
	public CommGlobalGroup getGlobalGroup(String code) {
		return dao.getGlobalGroup(code);
	}

	@Override
	public CommLanguage getLanguage(String code) {
		return dao.getLanguage(code);
	}

	@Override
	public CommMajor getMajor(String code) {
		return dao.getMajor(code);
	}

	@Override
	public CommSalary getSalary(String code) {
		return dao.getSalary(code);
	}

	@Override
	public CommWorkExperience getWorkExperience(String code) {
		return dao.getWorkExperience(code);
	}

	@Override
	public CommJobType getJobType(String code) {
		return dao.getJobType(code);
	}

}
