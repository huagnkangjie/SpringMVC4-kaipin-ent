package com.kaipin.search;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(value=SpringJUnit4ClassRunner.class)
 
@ContextConfiguration(locations = { "classpath:application-context.xml","classpath:quartz-task.xml"})

public abstract class JUnitBase  extends AbstractJUnit4SpringContextTests {

	
}
