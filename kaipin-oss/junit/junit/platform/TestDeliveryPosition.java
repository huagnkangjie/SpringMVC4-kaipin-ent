package junit.platform;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kaipin.oss.common.page.GenericDefaultPage;
import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.count.CountBase;
import com.kaipin.oss.service.count.ICountBaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context.xml","classpath*:kaipin-context.xml" })
public class TestDeliveryPosition {
	
	@Autowired
	private ICountBaseService service;
	
	@Test
	public void test(){
		Map<String,Object> param = new HashMap<String,Object>();
		IGenericPage<CountBase> pages = 
				service.getDeliveryPositionList(param, GenericDefaultPage.cpn(1),
				5);
		System.out.println(pages.getThisPageElements());
	}

}
