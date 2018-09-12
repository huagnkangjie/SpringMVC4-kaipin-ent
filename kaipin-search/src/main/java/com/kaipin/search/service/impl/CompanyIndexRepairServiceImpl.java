package com.kaipin.search.service.impl;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.kaipin.search.constant.Lucene;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.manager.CompanyModelFactory;
import com.kaipin.search.manager.PositionModelFactory;
import com.kaipin.search.service.IndexRepairService;

@Service("companyIndexRepairService")
public class CompanyIndexRepairServiceImpl implements IndexRepairService {
    private final static Log log = LogFactory.getLog(CompanyIndexRepairServiceImpl.class);
	public static final String ID = "id";
	public static final String ENT_NAME = "ent_name";
	public static final String OFFICE_AREA = "office_area";

	public static final String INDUSTRY_CODE = "industry_code";

	public static final String LAST_UPDATED_TIME = "last_updated_time"; // 修改时间

	/**
	 * @see com.kaipin.search.service.IndexRepairService#createIndex(Map) 
	 * 
	 */
	@Override
	public boolean createIndex(Map<String, Object> map) {
	 
		
		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());

			String path = Lucene.IndexType.company.getPath();

			indexHolder.add(path, Arrays.asList(CompanyModelFactory.create (map)));

			indexHolder.optimize(path);

			return true;
		} catch (Exception e1) {
            log.info(e1.getMessage());
			e1.printStackTrace();
		}

		return false;
 
	}

public boolean updateIndex(Map<String, Object> map) {
		
		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());
 
			String path = Lucene.IndexType.company.getPath();
			
			indexHolder.update (path, Arrays.asList(CompanyModelFactory.create  (map)));

			indexHolder.optimize(path);

			return true;
		} catch (Exception e) {
	          log.info(e .getMessage());
			e.printStackTrace();
		}

		
		
		return false;
	}

	@Override
	public boolean deleteIndex(String id) {

		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());


			String path = Lucene.IndexType.company.getPath();
			
			indexHolder.delete(path, Arrays.asList(CompanyModelFactory.create (id)));

			indexHolder.optimize(path);

			return true;
		} catch (Exception e) {
	          log.info(e .getMessage());
			e.printStackTrace();
		}

		return false;
	}


}
