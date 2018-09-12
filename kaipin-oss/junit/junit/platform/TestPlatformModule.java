package junit.platform;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.platform.PlatformModule;
import com.kaipin.oss.model.platform.PlatformOrganization;
import com.kaipin.oss.model.platform.PlatformPermission;
import com.kaipin.oss.model.platform.PlatformRole;
import com.kaipin.oss.model.platform.PlatformUser;
import com.kaipin.oss.service.company.CompanyInfoBaseService;
import com.kaipin.oss.service.platform.PlatformModuleService;
import com.kaipin.oss.service.platform.PlatformUserService;
import com.kaipin.oss.util.UuidUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context.xml","classpath*:kaipin-context.xml" })
public class TestPlatformModule {
	@Autowired
	private PlatformModuleService  platformModuleService;
	
	@Autowired
	private CompanyInfoBaseService service;
	
	@Test
	public void  tree(){
//		PlatformModule function = new PlatformModule();
//		function.setDescription("discription");
//		function.setName("name");
//		function.setSn("enName");
//		function.setParentId(Long.valueOf("1"));
//		function.setIcon("list");
//		function.setPriority(Integer.valueOf("999"));
//		function.setUrl("#");
//		boolean flag = platformModuleService.insertModule(function);
		boolean flag = platformModuleService.delModule("10022");
		System.out.println(">>>>>>>>>>>>>>"+flag);
		//		List<PlatformModule> list = platformModuleService.getListByParentId(parentId);
		
//		 PlatformModule  list=platformModuleService.getPermissionMenu();
//		
//		
//		 PlatformModule   list2=list;
//		 System.out.println(list);

	} 

	
//	PlatformRole  role  =user.getRole();
//	System.out.println(role.getRolePermissions());

	
 
	
	public static void main(String[] args) {
		 
		
		

	}

}
