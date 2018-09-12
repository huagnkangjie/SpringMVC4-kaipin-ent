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
import com.kaipin.search.service.LuceneIndexFactory;

@Service("companyIndexHandler")
public class CompanyIndexHandler implements TaskHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(LiveExpireResourceTask.class);

    @Autowired
    @Qualifier("companyInfoDao")
    private IndexBuildDao companyInfoDao; // 公司

    @Autowired
    private LuceneIndexFactory luceneIndexFactory; // 索引构建

    @Override
    public boolean objType(byte objType) {

        return SearchTask.ObjType.company == objType;
    }

    @Override
    public boolean incrementIndex(SearchLuceneTasks bean) {
        byte btmp = (byte) ((bean.getObjType().byteValue()) + bean.getOpt().byteValue());
        Map<String, Object> map;
        // 公司
        if (btmp == ((SearchTask.ObjType.company  ) +SearchTask.OptType.add)) {
            LOGGER.info("[公司索引] add");
            map = (Map<String, Object>) companyInfoDao.selectByPrimaryKey(bean.getObjId());

            luceneIndexFactory.createCompanyIndexInner(map);

            return true;
        }

        if (btmp == ((SearchTask.ObjType.company  )+ SearchTask.OptType.update)) {
            LOGGER.info("[公司索引] update");
            map = (Map<String, Object>) companyInfoDao.selectByPrimaryKey(bean.getObjId());

            luceneIndexFactory.updateCompanyIndexInner(map);
            return true;
        }



        if (btmp == ((SearchTask.ObjType.company  )+ SearchTask.OptType.delete)) {
            luceneIndexFactory.deleteCompanyIndex(bean.getObjId());
            LOGGER.info("[公司索引] delete");
            return true;
        }

        return false;
    }

}
