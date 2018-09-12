package com.kaipin.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kaipin.search.common.page.IGenericPage;
import com.kaipin.search.constant.Lucene;
import com.kaipin.search.constant.Lucene.IndexType;
import com.kaipin.search.constant.SearchTask.ObjType;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.core.index.Searchable;
import com.kaipin.search.entity.SearchExpireObject.SearchExpireObjectTable;
import com.kaipin.search.manager.LiveModelFactory;
import com.kaipin.search.manager.PositionModelFactory;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.entity.LiveInfo.LiveInfoTable;
import com.kaipin.search.entity.PositionInfo.PositionInfoTable;
import com.kaipin.search.repository.dao.FeedDao;
import com.kaipin.search.repository.dao.SearchExpireObjectDao;
import com.kaipin.search.repository.dao.expire.ExpireResourceDao;
import com.kaipin.search.service.ExpireResourceService;
import com.kaipin.search.util.DateFormatUtils;


@Service
public class ExpireResourceServiceImpl implements ExpireResourceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExpireResourceServiceImpl.class);

    @Autowired
    private SearchExpireObjectDao searchExpireObjectDao;

    @Autowired
    @Qualifier("LiveInfoExpireDao")
    private ExpireResourceDao liveInfoExpireDao;

    @Autowired
    @Qualifier("PositionExpireDao")
    private ExpireResourceDao positionExpireDao;

    @Autowired
    private FeedDao feedDao;


    @Override
    public int handleExpireResource() {

        return 0;
        // return handleExpirePosition() +handleExpireLive() ;
    }



    @Override
    public int handleExpirePositionResource() {

        return handleExpirePosition();
    }



    @Override
    public int handleExpireLiveResource() {

        return handleExpireLive();
    }



    /**
     * 过期视频
     * 
     * @return
     */
    private int handleExpireLive() {

        LOGGER.info("开始处理handleExpireLive ,time->"
                + DateFormatUtils.format2Date2(System.currentTimeMillis()));

        int pageNo = 1;

        int pageSize = Lucene.DEFAULT_COUNT;

        IGenericPage page;
        int ic = 0;
        String id = "";

        IndexHolder indexHolder = null;

        try {
            indexHolder = IndexHolder.init(Lucene.getLuceneDir());
        } catch (IOException e1) {

            e1.printStackTrace();
        }



        while (true) {

            page = liveInfoExpireDao.getExpireSearch(null, pageNo, pageSize);

            LOGGER.info("handleExpireLive ,size->[" + page.getTotalCount() + "]");


            if (page.getPageElementCount() > 0) {

                List<Map<String, Object>> list = page.getThisPageElements();

                for (Map<String, Object> content : list) {

                    try {
                        id = MapUtils.getString(content, LiveInfoTable.ID, "");

                        if (indexHolder != null) {

                            remove(indexHolder, IndexType.live.getPath(),
                                    LiveModelFactory.create(LiveModelFactory.db2Store(content)));

                            createExpireObject(id, ObjType.live);


                            ic++;
                        }


                    } catch (Exception e) {
                        LOGGER.info(e.getMessage());
                    }


                    feedDao.updateLogicDelByResourceId(id);
                    
                }

            } else {
                break;
            }

            if (page.hasNextPage()) {

                pageNo++;
            } else {

                break;
            }

        }
        LOGGER.info("结束处理handleExpireLive ,time->"
                + DateFormatUtils.format2Date2(System.currentTimeMillis()));

        return ic;

    }

    /**
     * 过期职位
     * 
     * @return
     */
    private int handleExpirePosition() {

        LOGGER.info("开始处理handleExpirePosition ,time->"
                + DateFormatUtils.format2Date2(System.currentTimeMillis()));

        int pageNo = 1;

        int pageSize = Lucene.DEFAULT_COUNT;

        IGenericPage page;
        int ic = 0;
        String id = "";

        IndexHolder indexHolder = null;

        try {
            indexHolder = IndexHolder.init(Lucene.getLuceneDir());
        } catch (IOException e1) {

            e1.printStackTrace();
        }



        while (true) {

            page = positionExpireDao.getExpireSearch(null, pageNo, pageSize);

            LOGGER.info("handleExpirePosition ,size->[" + page.getTotalCount() + "]");

            if (page.getPageElementCount() > 0) {

                List<Map<String, Object>> list = page.getThisPageElements();

                for (Map<String, Object> content : list) {

                    try {
                        id = MapUtils.getString(content, PositionInfoTable.ID, "");

                        if (indexHolder != null) {

                            remove(indexHolder, IndexType.position.getPath(), PositionModelFactory
                                    .create(PositionModelFactory.db2Store(content)));

                            createExpireObject(id, ObjType.position);
                            ic++;
                        }

                        feedDao.deleteByResourceId(id);// 删除动态里过期资源
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }

                }

            } else {
                break;
            }

            if (page.hasNextPage()) {

                pageNo++;
            } else {

                break;
            }

        }

        LOGGER.info("结束处理handleExpirePosition ,time->"
                + DateFormatUtils.format2Date2(System.currentTimeMillis()));

        return ic;
    }

    private synchronized void createExpireObject(String id, byte objType) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(SearchExpireObjectTable.ID, UUID.randomUUID().toString().toLowerCase());

        map.put(SearchExpireObjectTable.CREATE_TIME, System.currentTimeMillis());
        map.put(SearchExpireObjectTable.OBJ_ID, id);

        map.put(SearchExpireObjectTable.OBJ_TYPE, objType);
        try {
            searchExpireObjectDao.insertLog(map);
        } catch (Exception e) {

        }

    }

    private void remove(IndexHolder indexHolder, String path, Searchable bean) {
        List<Searchable> list = new ArrayList<Searchable>();

        try {
            list.add(bean);
            int ic = indexHolder.delete(path, list);

            indexHolder.optimize(path);

        } catch (Exception e) {

        }

    }


}
