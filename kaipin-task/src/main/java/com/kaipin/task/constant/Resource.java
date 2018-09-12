package com.kaipin.task.constant;

public interface Resource {
    /**
     * token过期时间-秒
     */
    public static final int ACCESS_TOKEN_EXPIRATION_TIME = 30 * 60;

    /**
     * token key 前缀
     */
    public static final String ACCESS_TOKEN_CACHE_KEY_PREFIX = "access_token";
    
    
}
