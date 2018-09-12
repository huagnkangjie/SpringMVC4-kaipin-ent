package junit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(value=SpringJUnit4ClassRunner.class)
 
@ContextConfiguration(locations = { "classpath*:config/spring.xml","classpath*:config/spring-mvc.xml","classpath*:config/spring-myBatis.xml"})

public abstract class JUnitBase {

}
