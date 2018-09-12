package com.kaipin.search.manager.task;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kaipin.search.constant.SearchTask;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.job.LiveExpireResourceTask;
import com.kaipin.search.repository.dao.IndexBuildDao;
import com.kaipin.search.service.ExpireResourceService;
import com.kaipin.search.service.LuceneIndexFactory;

@Service("liveIndexHandler")
public class LiveIndexHandler implements TaskHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(LiveExpireResourceTask.class);

    @Autowired
    @Qualifier("liveInfoDao")
    private IndexBuildDao liveInfoDao;// 视频

    @Autowired
    private LuceneIndexFactory luceneIndexFactory; // 索引构建
    


    @Override
    public boolean objType(byte objType) {

        return SearchTask.ObjType.live == objType;
    }

    @Override
    public boolean incrementIndex(SearchLuceneTasks bean) {
        byte btmp = (byte) ((bean.getObjType().byteValue()) + bean.getOpt().byteValue());
        // 视频
     
        Map<String, Object> map;
        if (btmp == ((SearchTask.ObjType.live)+ SearchTask.OptType.add)) {

            map = (Map<String, Object>) liveInfoDao.selectByPrimaryKey(bean.getObjId());

               LOGGER.info("[视频索引] add");
            luceneIndexFactory.createLiveIndexInner(map);
 
            return true;
        }

        if (btmp == ((SearchTask.ObjType.live) + SearchTask.OptType.update)) {

            map = (Map<String, Object>) liveInfoDao.selectByPrimaryKey(bean.getObjId());
               LOGGER.info("[视频索引] update");
            luceneIndexFactory.updateLiveIndexInnder(map);
            
 

            return true;
        }

        if (btmp == ((SearchTask.ObjType.live)+ SearchTask.OptType.delete)) {
               LOGGER.info("[视频索引] delete");
            luceneIndexFactory.deleteLiveIndex(bean.getObjId());

            return true;
        }
 
   

        return false;
    }

}
