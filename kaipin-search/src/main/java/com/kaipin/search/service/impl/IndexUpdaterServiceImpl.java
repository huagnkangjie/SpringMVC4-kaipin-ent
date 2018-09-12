package com.kaipin.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kaipin.search.common.page.IGenericPage;
import com.kaipin.search.constant.Lucene;
import com.kaipin.search.constant.SearchTask;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.repository.dao.IndexBuildDao;
import com.kaipin.search.repository.dao.SearchLuceneTasksDao;
import com.kaipin.search.service.IndexUpdaterService;
import com.kaipin.search.service.LuceneIndexFactory;
import com.kaipin.search.service.SearchLuceneTasksService;


/**
 * 定时更新服务
 * 
 *
 */
@Service
public class IndexUpdaterServiceImpl implements IndexUpdaterService {


    @Autowired
    private SearchLuceneTasksService searchLuceneTasksService;

    @Override
    public void start() {
        
                searchLuceneTasksService.excuteUnHandleTask();

     

    }



}
