package com.kaipin.search.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.search.service.ExpireResourceService;

/**
 * 视频过期处理
 * 
 *
 */
public class LiveExpireResourceTask implements JobTask{
    private final static Logger LOGGER = LoggerFactory.getLogger(LiveExpireResourceTask.class);

    @Autowired
    private ExpireResourceService expireResourceService;
    
    @Override
    public void execute() {
        
        LOGGER.info("handleExpireLiveResource task job ");
 
        expireResourceService.handleExpireLiveResource();
        
    }

}
