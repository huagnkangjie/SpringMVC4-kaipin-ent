package com.kaipin.search.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.kaipin.search.constant.Lucene;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.manager.PositionModelFactory;
import com.kaipin.search.service.IndexRepairService;

@Service("positionIndexRepairService")
public class PositionIndexRepairServiceImpl implements IndexRepairService {
    private final static Log log = LogFactory.getLog(PositionIndexRepairServiceImpl.class);
	public static final String ID = "id";
	public static final String LOCATION_CODE = "location_code";

	public static final String POSITION_NAME = "position_name";
 
	public static final String INDUSTRY_CODE = "industry_code";

	public static final String LAST_UPDATED_TIME = "last_updated_time"; // 修改时间
	
	
 
	
	@Override
	public boolean createIndex(Map<String, Object> map) {

		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());

			String path = Lucene.IndexType.position.getPath();

			indexHolder.add(path, Arrays.asList(PositionModelFactory.create (map)));

			indexHolder.optimize(path);

			return true;
		} catch (Exception e1) {
		    log.info(e1.getMessage());
			e1.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateIndex(Map<String, Object> map) {
		
		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());
 
			String path = Lucene.IndexType.position.getPath();
			
			indexHolder.update (path, Arrays.asList(PositionModelFactory.create (map)));

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


			String path = Lucene.IndexType.position.getPath();
			
			indexHolder.delete(path, Arrays.asList(PositionModelFactory.create (id)));

			indexHolder.optimize(path);

			return true;
		} catch (Exception e) {
		    log.info(e .getMessage());
			e.printStackTrace();
		}

		return false;
	}

}
