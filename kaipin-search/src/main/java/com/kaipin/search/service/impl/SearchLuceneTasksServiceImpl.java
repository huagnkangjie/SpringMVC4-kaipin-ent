package com.kaipin.search.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaipin.search.common.page.IGenericPage;
import com.kaipin.search.constant.Lucene;
import com.kaipin.search.constant.SearchTask;
import com.kaipin.search.constant.SearchTask.StatusType;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.manager.task.TaskContext;
import com.kaipin.search.repository.dao.IndexBuildDao;
import com.kaipin.search.repository.dao.SearchExpireObjectDao;
import com.kaipin.search.repository.dao.SearchLuceneTasksDao;
import com.kaipin.search.service.LuceneIndexFactory;
import com.kaipin.search.service.SearchLuceneTasksService;
import com.kaipin.search.util.bean.BeanMapUtil;

@Service
@Transactional
public class SearchLuceneTasksServiceImpl implements SearchLuceneTasksService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExpireResourceServiceImpl.class);

    @Autowired
    private SearchLuceneTasksDao searchLuceneTasksDao; // 增量任务

    @Autowired
    private LuceneIndexFactory luceneIndexFactory; // 索引构建

    @Autowired
    private TaskContext taskContext;

    @Autowired
    private SearchExpireObjectDao searchExpireObjectDao;


    @Override
    public boolean incrementIndex(SearchLuceneTasks bean) {

        return taskContext.operation(bean);
        // return false; //test

    }



    @Override
    public int updateHandled(String taskId) {
        return searchLuceneTasksDao.updateHandled(taskId);

    }



    @Override
    public   void  excuteUnHandleTask() {
        
        LOGGER.info( "[处理任务]  ->excuteUnHandleTask"  );
        int pageNo = 1;

        int pageSize = Lucene.DEFAULT_COUNT;

        IGenericPage page;

        while (true) {

            page = searchLuceneTasksDao.selectUnHandle(pageNo, pageSize);


            if (page.getPageElementCount() > 0) {

                List<SearchLuceneTasks> list = page.getThisPageElements();

                try {
                    buildTask(list);
                } catch (Exception e) {
                    LOGGER.info( "[处理任务] 异常出现->" + e.getMessage());

                }

            }

            if (page.hasNextPage()) {

                pageNo++;
            } else {

                break;
            }

        }

    }

    @Override
    public boolean handleLuceneTask(Map<String, Object> map) {

        try {

            SearchLuceneTasks bean = new SearchLuceneTasks();
            bean.setId(UUID.randomUUID().toString().toLowerCase());
            bean.setCreateTime(System.currentTimeMillis());
            bean.setStatus(StatusType.un_handle);
            bean.setObjId(MapUtils.getString(map, SearchLuceneTasksService.TASK_OBJ_ID));
            bean.setObjType(MapUtils.getByte(map, SearchLuceneTasksService.TASK_OBJ_TYPE));
            bean.setOpt(MapUtils.getByte(map, SearchLuceneTasksService.TASK_OPT));

            if (!incrementIndex(bean)) {// 未建立放入task
                map.put(SearchLuceneTasksService.TASK_ID, bean.getId());
                map.put(SearchLuceneTasksService.TASK_STATUS, bean.getStatus());
                map.put(SearchLuceneTasksService.TASK_CREATE_TIME, bean.getCreateTime());

                searchLuceneTasksDao.insertLuceneTask(map);


            }

            searchExpireObjectDao.deleteLog(bean.getObjId());// 清理过期资源记录

            return true;
        } catch (Exception e) {

            e.printStackTrace();
        }



        return false;
    }

    private void buildTask(List<SearchLuceneTasks> list) {

        LOGGER.info("[处理任务] size(" + list.size() + ")");


        for (SearchLuceneTasks bean : list) {
            LOGGER.info("[索引] "+bean.toString());
       
            if (incrementIndex(bean)) {
            
                LOGGER.info("[更新状态] "+bean.getId());
                searchLuceneTasksDao.updateHandled(bean.getId());
            }

        }


//        if (taskList.size() > 0) {
//            searchLuceneTasksDao.updateBatchHandle(taskList);
//            taskList.clear();
//            taskList = null;
//        }

    }


}
