package com.kaipin.search.manager.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kaipin.search.constant.SearchTask;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.repository.dao.IndexBuildDao;
import com.kaipin.search.service.LuceneIndexFactory;

/**
 * 学校索引
 *
 * 
 */
@Service("schoolIndexHandler")
public class SchoolIndexHandler implements TaskHandler {
    @Autowired
    @Qualifier("schInfoDao")
    private IndexBuildDao schInfoDao;

    @Autowired
    private LuceneIndexFactory luceneIndexFactory; // 索引构建

    @Override
    public boolean objType(byte objType) {

        return SearchTask.ObjType.school == objType;
    }

    @Override
    public boolean incrementIndex(SearchLuceneTasks bean) {
        byte btmp = (byte) ((bean.getObjType().byteValue())+ bean.getOpt().byteValue());
        Map<String, Object> map;
        // 职位
        if (btmp == ((SearchTask.ObjType.school) + SearchTask.OptType.add)) {

            map = schInfoDao.selectByPrimaryKey(bean.getObjId());

            luceneIndexFactory.createSchInfoIndexInner (map);

            return true;
        }

        if (btmp == ((SearchTask.ObjType.school) + SearchTask.OptType.update)) {

            map = (Map<String, Object>) schInfoDao.selectByPrimaryKey(bean.getObjId());

            luceneIndexFactory.updateSchInfoIndexInnder (map);
            return true;
        }

        if (btmp == ((SearchTask.ObjType.school)+ SearchTask.OptType.delete)) {
            luceneIndexFactory.deleteSchInfoIndex(bean.getObjId());

            return true;
        }

        return false;
    }

}
