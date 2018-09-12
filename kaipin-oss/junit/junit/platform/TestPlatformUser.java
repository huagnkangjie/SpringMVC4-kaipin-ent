package junit.platform;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.platform.PlatformOrganization;
import com.kaipin.oss.model.platform.PlatformPermission;
import com.kaipin.oss.model.platform.PlatformRole;
import com.kaipin.oss.model.platform.PlatformUser;
import com.kaipin.oss.service.platform.PlatformUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context.xml","classpath*:kaipin-context.xml" })
public class TestPlatformUser {
@Autowired
	private PlatformUserService platformUserService;
	
	@Test
	public void  findByUserName(){
		

		IGenericPage<PlatformUser> page=platformUserService.getPage(null,1,5);
		
	PlatformUser user=	platformUserService.findByUsername("admin");
		
PlatformOrganization  organize =user.getOrganization();
	
	//System.out.println(organize);
	
	
	List<PlatformRole> roles=organize.getOrganizationRoles();

	for (PlatformRole platformRole : roles) {
		
		System.out.println(platformRole.toString());
		
	 List<PlatformPermission>  list= platformRole.getRolePermissions();
		
	 
		for ( PlatformPermission permission  : list) {
			
			System.out.println(permission.getSn());
			
		}
	 

	} 

	
//	PlatformRole  role  =user.getRole();
//	System.out.println(role.getRolePermissions());

	
	}
	
	public static void main(String[] args) {
		 
		
		

	}

}
