package com.kaipin.search.manager.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kaipin.search.constant.SearchTask;
import com.kaipin.search.entity.SearchLuceneTasks;
import com.kaipin.search.repository.dao.IndexBuildDao;
import com.kaipin.search.service.LuceneIndexFactory;

@Service("positionIndexHandler")
public class PositionIndexHandler implements TaskHandler {
    @Autowired
    @Qualifier("positionInfoDao")
    private IndexBuildDao positionInfoDao; // 职位

    @Autowired
    private LuceneIndexFactory luceneIndexFactory; // 索引构建

    @Override
    public boolean objType(byte objType) {

        return SearchTask.ObjType.position == objType;
    }

    @Override
    public boolean incrementIndex(SearchLuceneTasks bean) {
        byte btmp = (byte) ((bean.getObjType().byteValue()) + bean.getOpt().byteValue());
        Map<String, Object> map;
        // 职位
        if (btmp == ((SearchTask.ObjType.position  ) + SearchTask.OptType.add)) {

            map = positionInfoDao.selectByPrimaryKey(bean.getObjId());

            luceneIndexFactory.createPositionIndexInner(map);

            return true;
        }

        if (btmp == ((SearchTask.ObjType.position  ) + SearchTask.OptType.update)) {

            map = (Map<String, Object>) positionInfoDao.selectByPrimaryKey(bean.getObjId());

            luceneIndexFactory.updatePositionIndexInner(map);
            return true;
        }

        if (btmp == ((SearchTask.ObjType.position  )+SearchTask.OptType.delete)) {
            luceneIndexFactory.deletePositionIndex(bean.getObjId());

            return true;
        }

        return false;
    }

}
