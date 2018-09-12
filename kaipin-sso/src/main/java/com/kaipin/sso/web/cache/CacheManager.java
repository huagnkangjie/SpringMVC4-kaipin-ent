package com.kaipin.sso.web.cache;

import com.baomidou.kisso.SSOCache;

public interface CacheManager {

	SSOCache  getCache();
    
    void setCache(SSOCache cache);

}
