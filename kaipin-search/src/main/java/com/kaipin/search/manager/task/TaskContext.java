package com.kaipin.search.manager.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kaipin.search.common.spring.SpringContextHolder;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.job.LiveExpireResourceTask;

@Component
public class TaskContext {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaskContext.class);

    private static List<TaskHandler> listTask = new ArrayList<TaskHandler>();

    private void init() {
        //初始载入
        try {
            listTask.clear();
            listTask.add(SpringContextHolder.getBean( LiveIndexHandler.class));
            listTask.add(SpringContextHolder.getBean( CompanyIndexHandler.class));
            listTask.add(SpringContextHolder.getBean(PositionIndexHandler.class));
            listTask.add(SpringContextHolder.getBean( StudentIndexHandler.class));
            listTask.add(SpringContextHolder.getBean( SchoolIndexHandler.class));
 
        } catch (Exception e) {
            LOGGER.info("[初始化索引对象]异常->"+e.getMessage());
            e.printStackTrace();
        }


    }

    /**
     * 任务动作
     * @param bean 任务对象
     * @return
     */
    public synchronized boolean operation(SearchLuceneTasks bean) {
        if( listTask.size()==0) {
            init();
        }
        LOGGER.info("[对象] "+listTask.toString());
        
        for (int pos = 0, len = listTask.size(); pos < len; pos++) {

            if (listTask.get(pos).objType(bean.getObjType())) {
                LOGGER.info("[处理对象] "+listTask.get(pos).toString());
                return listTask.get(pos).incrementIndex(bean);

            }

        }

        return false;

    }


}
