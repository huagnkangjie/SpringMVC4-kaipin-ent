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
 * 学生索引
 *
 * 
 */
@Service("studentIndexHandler")
public class StudentIndexHandler implements TaskHandler {
    @Autowired
    @Qualifier("stuUserBuildDao")
    private IndexBuildDao stuUserBuildDao;

    @Autowired
    private LuceneIndexFactory luceneIndexFactory; // 索引构建

    @Override
    public boolean objType(byte objType) {

        return SearchTask.ObjType.student == objType;
    }

    @Override
    public boolean incrementIndex(SearchLuceneTasks bean) {
        byte btmp = (byte) ((bean.getObjType().byteValue()) +bean.getOpt().byteValue());
        Map<String, Object> map;
     
        if (btmp == ((SearchTask.ObjType.student) + SearchTask.OptType.add)) {

            map = stuUserBuildDao.selectByPrimaryKey(bean.getObjId());

            luceneIndexFactory.createStuUserIndexInner  (map);

            return true;
        }

        if (btmp == ((SearchTask.ObjType.student) + SearchTask.OptType.update)) {

            map = (Map<String, Object>) stuUserBuildDao.selectByPrimaryKey(bean.getObjId());

            luceneIndexFactory.updateStuUserIndexInnder (map);
            return true;
        }

        if (btmp == ((SearchTask.ObjType.student) + SearchTask.OptType.delete)) {
            luceneIndexFactory.deleteStuUserIndex(bean.getObjId());

            return true;
        }

        return false;
    }

}
