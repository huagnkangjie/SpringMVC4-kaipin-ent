package com.kaipin.sso.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaipin.sso.constant.enums.UserType;
import com.kaipin.sso.entity.web.school.SchoolUserInfo;
import com.kaipin.sso.service.web.company.ICompanyBaseUserService;
import com.kaipin.sso.service.web.school.ISchoolUserInfoService;

@Component("userManager")
public class UserManager {
	@Autowired
	private ISchoolUserInfoService schoolUserInfoService;
	@Autowired
	private ICompanyBaseUserService companyBaseUserService;

	public String getUserGroupId(String id, String userType) {

		if (userType == null || userType.equals("")) {

			return "";
		}

		Integer iUserType = Integer.valueOf(userType);

		if (iUserType.intValue() == UserType.company.getValue()) {

			return companyBaseUserService.getUserGroupId(id);

		} else if (iUserType.intValue() == UserType.university.getValue()) {

			return schoolUserInfoService.getUserGroupId(id);

		} else {

			return id;
		}

	}

}
