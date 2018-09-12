package com.kaipin.search.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.search.service.ExpireResourceService;

/**
 *  过期职位处理,每天凌晨处理
 * 
 *
 */
public class PositionExpireResourceTask implements JobTask{
    private final static Logger LOGGER = LoggerFactory.getLogger(LiveExpireResourceTask.class);

    @Autowired
    private ExpireResourceService expireResourceService;
    
    @Override
    public void execute() {
        LOGGER.info("handleExpirePositionResource task job ");
        expireResourceService.handleExpirePositionResource();
        
    }

}
