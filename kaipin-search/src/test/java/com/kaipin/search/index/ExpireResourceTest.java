package com.kaipin.search.index;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.search.JUnitBase;
import com.kaipin.search.service.ExpireResourceService;

public class ExpireResourceTest extends JUnitBase {

    @Autowired
    private ExpireResourceService expireResourceService;
    
   @Test
    public void testPosition(){
        
        expireResourceService.handleExpirePositionResource();
    }
   
   //@Test
    public void testLive(){
        
        expireResourceService.handleExpireLiveResource();
    }
}
