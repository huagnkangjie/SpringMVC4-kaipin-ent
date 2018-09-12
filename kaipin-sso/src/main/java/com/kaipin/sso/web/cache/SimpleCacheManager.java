package com.kaipin.sso.web.cache;

 
import org.springframework.beans.factory.InitializingBean;

import com.baomidou.kisso.SSOCache;
import com.kaipin.sso.web.cache.impl.WebRedisStore;

public class SimpleCacheManager implements InitializingBean, CacheManager {
    
    private SSOCache cache;

 
    @Override
    public void afterPropertiesSet() throws Exception {
        if(cache == null){
            cache = new WebRedisStore();
        }
    }



	@Override
	public SSOCache getCache() {
	 
		return cache;
	}



	@Override
	public void setCache(SSOCache cache) {
 
		this.cache=cache;
		
	}

}
