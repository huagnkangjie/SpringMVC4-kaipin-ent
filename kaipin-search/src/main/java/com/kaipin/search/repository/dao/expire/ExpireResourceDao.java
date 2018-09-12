package com.kaipin.search.repository.dao.expire;

import java.util.Map;

import com.kaipin.search.common.page.IGenericPage;
import com.kaipin.search.repository.mybatis.dao.MybatisBaseDAO;

/**
 * 过期视频
 *  
 *
 */
public interface ExpireResourceDao {

    /**
     * 查询过期视频
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public IGenericPage getExpireSearch (Map<String, Object> param, int pageNo, int pageSize);
    
    public long  getExpireSearchCount();
    
}
