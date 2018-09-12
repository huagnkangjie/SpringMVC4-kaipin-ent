package com.kaipin.sso.web.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baomidou.kisso.SSOCache;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import com.kaipin.sso.Constant;
import com.kaipin.sso.nosql.redis.RedisFactory;
import com.kaipin.sso.nosql.redis.RedisHelper;

public class WebRedisStore implements SSOCache {

	private static Logger log = Logger.getLogger(WebRedisStore.class);

	public static final String TOKEN_PREFIX = "web:";

	public static final String TOKEN_FIELD = "token_field";

	public static final String USER_FIELD = "user_field";

	public static final String AUTH_TYPE = "web";

 

	@Override
	public Token get(String key, int expires) {

		RedisHelper redisHelper = null;

		if(key==null){
			return null;
		}
		
		
		
		try {
			redisHelper = RedisFactory.getRedisHelper();
 
			String userField = redisHelper.hget(key, USER_FIELD);

			Token token = SSOHelper.getKissoService().getConfig().getToken().parseToken(userField);

			if (token != null) {
				return token;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (redisHelper != null) {
				redisHelper.release();
			}
		}

		return null;
	}

	@Override
	public boolean set(String key, Token token, int expires) {

		RedisHelper redisHelper = null;
		
		if(key==null|| token==null){
			return false;
		}

		try {
			redisHelper = RedisFactory.getRedisHelper();



			Map<String, String> data = new HashMap<String, String>();

			data.put(USER_FIELD, token.jsonToken());
 
			redisHelper.hmset(key, data);
 
			redisHelper.expire(key, expires);

			return true;

		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (redisHelper != null) {
				redisHelper.release();
			}
		}

		return false;
	}

	@Override
	public boolean delete(String key) {
		RedisHelper redisHelper = null;

		if(key==null){
			return false;
		}
		try {
			redisHelper = RedisFactory.getRedisHelper();

			String keyToken = key;

			redisHelper.del(keyToken);

			return true;
			
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (redisHelper != null) {
				redisHelper.release();
			}
		}

		return false;
	}

}
