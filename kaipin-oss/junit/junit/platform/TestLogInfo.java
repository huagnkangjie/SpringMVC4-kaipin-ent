package junit.platform;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaipin.oss.presentation.action.main.IndexAction;

public class TestLogInfo {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestLogInfo.class); 
	Logger monitorLogger = LoggerFactory.getLogger("monitor");  
	@Test
	public void  test(){
		
		monitorLogger.info("123");
		LOGGER.error("test");LOGGER.info ("test");
	}
}
