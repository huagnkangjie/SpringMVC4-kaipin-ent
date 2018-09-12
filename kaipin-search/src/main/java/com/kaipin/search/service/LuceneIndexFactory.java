package com.kaipin.search.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.manager.CompanyModelFactory;
import com.kaipin.search.manager.LiveModelFactory;
import com.kaipin.search.manager.PositionModelFactory;
import com.kaipin.search.manager.SchInfoModelFactory;
import com.kaipin.search.manager.StuUserModelFactory;
import com.kaipin.search.repository.dao.SearchLuceneTasksDao;
import com.kaipin.search.service.impl.CompanyIndexRepairServiceImpl;
import com.kaipin.search.service.impl.LiveIndexRepairServiceImpl;
import com.kaipin.search.service.impl.PositionIndexRepairServiceImpl;

/**
 * 索引管理
 * 
 */
@Component
public class LuceneIndexFactory {

    @Autowired
    @Qualifier("positionIndexRepairService")
    private IndexRepairService positionIndexRepairService;

    @Autowired
    @Qualifier("companyIndexRepairService")
    private IndexRepairService companyIndexRepairService;

    @Autowired
    @Qualifier("liveIndexRepairService")
    private IndexRepairService liveIndexRepairService;

    @Autowired
    @Qualifier("stuUserIndexRepairService")
    private IndexRepairService stuUserIndexRepairService;

    @Autowired
    @Qualifier("schInfoIndexRepariService")
    private IndexRepairService schInfoIndexRepariService;

    @Autowired
    private SearchLuceneTasksDao searchLuceneTasksDao;

    @Autowired
    private SearchLuceneTasksService searchLuceneTasksService;



    /**
     *  索引任务处理
     * @deprecated
     * @param map
     * @return
     */
    public boolean insertLuceneTask(Map<String, Object> map) {
     return operationLuceneTask(map);
    }
    
    public boolean operationLuceneTask(Map<String, Object> map) {
        boolean flag = searchLuceneTasksService.handleLuceneTask(map);

        return flag;
    }
    
    


    /**
     * add职位 索引
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean createPositionIndex(String taskId, Object param) {

        boolean b =
                positionIndexRepairService.createIndex(PositionModelFactory.api2Store((Map) param));

        searchLuceneTasksDao.updateHandled(taskId);

        return b;

    }

    public boolean createPositionIndexInner(Object param) {

        return positionIndexRepairService.createIndex(PositionModelFactory.db2Store((Map) param));

    }

    /**
     * 更新职位 索引
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean updatePositionIndex(String taskId, Object param) {

        boolean b =
                positionIndexRepairService.updateIndex(PositionModelFactory.api2Store((Map) param));
        searchLuceneTasksDao.updateHandled(taskId);

        return b;

    }

    public boolean updatePositionIndexInner(Object param) {

        return positionIndexRepairService.updateIndex(PositionModelFactory.db2Store((Map) param));

    }

    /**
     * 删除职位 索引
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean deletePositionIndex(String taskId, String param) {

        boolean b = positionIndexRepairService.deleteIndex(param);
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean deletePositionIndex(String param) {

        return positionIndexRepairService.deleteIndex(param);

    }

    /**
     * add公司 索引
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean createCompanyIndex(String taskId, Object param) {

        boolean b =
                companyIndexRepairService.createIndex(CompanyModelFactory.api2Store((Map) param));

        searchLuceneTasksDao.updateHandled(taskId);

        return b;
    }

    public boolean createCompanyIndexInner(Object param) {

        return companyIndexRepairService.createIndex(CompanyModelFactory.db2Store((Map) param));
    }


    public boolean updateCompanyIndexInner(Object param) {

        return companyIndexRepairService.updateIndex(CompanyModelFactory.db2Store((Map) param));
    }

    /**
     * 更新公司 索引
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean updateCompanyIndex(String taskId, Object param) {

        boolean b =
                companyIndexRepairService.updateIndex(CompanyModelFactory.db2Store((Map) param));

        searchLuceneTasksDao.updateHandled(taskId);
        return b;
    }

    /**
     * 删除公司 索引
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean deleteCompanyIndex(String taskId, String param) {

        boolean b = companyIndexRepairService.deleteIndex(param);

        searchLuceneTasksDao.updateHandled(taskId);

        return b;
    }

    public boolean deleteCompanyIndex(String param) {

        return companyIndexRepairService.deleteIndex(param);
    }

    /**
     * add宣讲会索引
     * 
     * @param param待更新参数
     * @return
     */
    public boolean createLiveIndex(String taskId, Object param) {

        boolean b = liveIndexRepairService.createIndex(LiveModelFactory.api2Store((Map) param));
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean createLiveIndexInner(Object param) {

        return liveIndexRepairService.createIndex(LiveModelFactory.db2Store((Map) param));

    }

    /**
     * 更新宣讲会 索引
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean updateLiveIndex(String taskId, Object param) {
        boolean b = liveIndexRepairService.updateIndex(LiveModelFactory.api2Store((Map) param));
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean updateLiveIndexInnder(Object param) {
        return liveIndexRepairService.updateIndex(LiveModelFactory.db2Store((Map) param));
    }

    /**
     * 删除宣讲会 索引
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean deleteLiveIndex(String taskId, String param) {
        boolean b = liveIndexRepairService.deleteIndex(param);
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean deleteLiveIndex(String param) {
        return liveIndexRepairService.deleteIndex(param);
    }


    /**
     * add 学生
     * 
     * @param param待更新参数
     * @return
     */
    public boolean createStuIndex(String taskId, Object param) {

        boolean b =
                stuUserIndexRepairService.createIndex(StuUserModelFactory.api2Store((Map) param));
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean createStuUserIndexInner(Object param) {

        return stuUserIndexRepairService.createIndex(StuUserModelFactory.db2Store((Map) param));

    }

    /**
     * 更新 学生
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean updateStuUserIndex(String taskId, Object param) {
        boolean b =
                stuUserIndexRepairService.updateIndex(StuUserModelFactory.api2Store((Map) param));
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean updateStuUserIndexInnder(Object param) {
        return stuUserIndexRepairService.updateIndex(StuUserModelFactory.db2Store((Map) param));
    }

    /**
     * 删除 学生
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean deleteStuUserIndex(String taskId, String param) {
        boolean b = stuUserIndexRepairService.deleteIndex(param);
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean deleteStuUserIndex(String param) {
        return stuUserIndexRepairService.deleteIndex(param);
    }


    /**
     * add 学校
     * 
     * @param param待更新参数
     * @return
     */
    public boolean createSchInfoIndex(String taskId, Object param) {

        boolean b =
                schInfoIndexRepariService.createIndex(SchInfoModelFactory.api2Store((Map) param));
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean createSchInfoIndexInner(Object param) {

        return schInfoIndexRepariService.createIndex(SchInfoModelFactory.db2Store((Map) param));

    }

    /**
     * 更新 学校
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean updateSchInfoIndex(String taskId, Object param) {
        boolean b =
                schInfoIndexRepariService.updateIndex(SchInfoModelFactory.api2Store((Map) param));
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean updateSchInfoIndexInnder(Object param) {
        return schInfoIndexRepariService.updateIndex(SchInfoModelFactory.db2Store((Map) param));
    }

    /**
     * 删除 学校
     * 
     * @param param 待更新参数
     * @return
     */
    public boolean deleteSchInfoIndex(String taskId, String param) {
        boolean b = schInfoIndexRepariService.deleteIndex(param);
        searchLuceneTasksDao.updateHandled(taskId);
        return b;

    }

    public boolean deleteSchInfoIndex(String param) {
        return schInfoIndexRepariService.deleteIndex(param);
    }



}
