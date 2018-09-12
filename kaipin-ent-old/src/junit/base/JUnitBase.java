package junit.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(value=SpringJUnit4ClassRunner.class)
 
@ContextConfiguration(locations = {  "classpath*:spring.xml",
		 "classpath*:spring-mvc.xml",
		 "classpath*:spring-myBatis.xml" })
public abstract class JUnitBase {

}
