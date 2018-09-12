package com.kaipin.sso.service.web.school;

 
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.sso.JUnitBase;
import com.kaipin.sso.entity.web.school.SchoolUserInfo;

 

public class SchoolUserInfoTest extends JUnitBase {
@Autowired
	private ISchoolUserInfoService schoolUserInfoService;
	@Test
	public void getById( ) {
		
		SchoolUserInfo bean=schoolUserInfoService.getById("0c603818-da47-44ae-9f14-90e322f04eaf");
		
		Assert.assertNotNull(bean);
		
	}
}
