package com.kaipin.sso.nosql.redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.kisso.SSOConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml","classpath:redis-context.xml" })
public class RedisTest {
 //	@Test
 	public void test(){
 		
 		
 		
 		SSOConfig config=	SSOConfig.getInstance();
 		
 		System.out.println(config);
 	}
 	@Test
	public void action() {
		RedisHelper redisHelper = null;
		try {
			redisHelper = RedisFactory.getRedisHelper();
			
			Map<String,String> data = new HashMap<String,String>();
			data.put("config", "config");
			data.put("createTime", "createTime");
			redisHelper.hmset("web:123", data);
			
			//redisHelper.set("test", "value1");
			Object value = redisHelper. hget ("web:123","config");
			System.out.println(value);
		} finally {
			if (redisHelper != null)
				redisHelper.release();
		}
	}

 
	public static void main(String[] args) {

	}

}
