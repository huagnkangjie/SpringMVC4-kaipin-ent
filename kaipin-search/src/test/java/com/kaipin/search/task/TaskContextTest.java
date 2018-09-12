package com.kaipin.search.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.kaipin.search.JUnitActionBase;
import com.kaipin.search.JUnitBase;
import com.kaipin.search.common.spring.SpringContextHolder;
import com.kaipin.search.constant.SearchTask;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.manager.task.LiveIndexHandler;
import com.kaipin.search.manager.task.TaskContext;

public class TaskContextTest extends JUnitBase   {
    @Autowired
    private TaskContext taskContext;
    

    
    
@Test
    public void test(){
    SearchLuceneTasks bean=new SearchLuceneTasks();
    bean.setObjType(SearchTask.ObjType.live);
    bean.setOpt(SearchTask.OptType.add);
    bean.setObjId("111");
    
    taskContext.operation(bean);
    
 
    
    }
}
