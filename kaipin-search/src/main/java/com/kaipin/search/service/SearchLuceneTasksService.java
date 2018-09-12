package com.kaipin.search.service;

import java.util.Map;

import com.kaipin.search.entity.SearchLuceneTasks;

public interface SearchLuceneTasksService {

    public static String TASK_ID = "id";
    public static String TASK_OBJ_ID = "obj_id";// 资源id
    public static String TASK_OBJ_TYPE = "obj_type";// 对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
    public static String TASK_OPT = "opt";// 操作类型(0-add,1-delete,2-update
    public static String TASK_CREATE_TIME = "create_time";
    public static String TASK_STATUS = "status";// 处理状态（0-未处理,1-已处理
    public static String TASK_HANDLE_TIME = "handle_time";

    /**
     * 
     * @param bean 索引任务对象
     * @return true 创建成功 false 失败
     */
    public boolean incrementIndex(SearchLuceneTasks bean);

    /**
     * 更新任务
     * 
     * @param taskId
     *          任务id
     * @return
     */
    public int updateHandled(String taskId);

    /**
     * 处理未建立索引的任务
     */
    public void excuteUnHandleTask();


    public boolean handleLuceneTask(Map<String, Object> map);

}
