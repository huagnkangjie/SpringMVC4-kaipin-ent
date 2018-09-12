package com.kaipin.search.service;

/**
 * 过期资源处理
 *  
 *
 */
public interface ExpireResourceService {

    
    public int handleExpireResource();
    
    public int handleExpirePositionResource();
    public int handleExpireLiveResource();
    
}
