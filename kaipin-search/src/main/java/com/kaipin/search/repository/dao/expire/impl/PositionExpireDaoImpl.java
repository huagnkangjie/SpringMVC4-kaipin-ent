package com.kaipin.search.repository.dao.expire.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.search.common.page.IGenericPage;
import com.kaipin.search.repository.dao.expire.ExpireResourceDao;
import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO;

/**
 * @author tan
 *
 */
@Repository("PositionExpireDao")
public class PositionExpireDaoImpl extends MybatisBaseDAO<Map, String>implements ExpireResourceDao {
 

    @Override
    public long getExpireSearchCount() {
         
      Long  count = getCountBy( "getExpireSearchCount", null);
      return count;
    }

    @Override
    public IGenericPage getExpireSearch(Map<String, Object> param, int pageNo, int pageSize) {
        try {
            IBasePageSql sql = new IBasePageSql() {

                @Override
                public String getPageListSqlName() {

                    return "getExpireSearch";
                }

                @Override
                public String getPageCountSqlName() {

                    return "getExpireSearchCount";
                }
            };

            IGenericPage test = findPageBy(sql, param, pageNo, pageSize, null, null);
 
            return findPageBy(sql, param, pageNo, pageSize, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getDefaultSqlNamespace() {

        return "PositionInfoMapper";
    }

}
