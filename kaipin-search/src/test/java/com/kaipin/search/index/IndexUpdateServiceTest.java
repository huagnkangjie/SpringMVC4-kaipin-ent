package com.kaipin.search.index;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.search.JUnitBase;
import com.kaipin.search.service.IndexUpdaterService;
import com.kaipin.search.service.SearchLuceneTasksService;

public class IndexUpdateServiceTest  extends JUnitBase{

    @Autowired
    private IndexUpdaterService indexUpdaterService;
	@Test
	public void test(){
	    indexUpdaterService.start();
	}

}
