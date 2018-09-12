package com.kaipin.search.service.impl;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.kaipin.search.constant.Lucene;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.manager.LiveModelFactory;
import com.kaipin.search.manager.StuUserModelFactory;
import com.kaipin.search.service.IndexRepairService;

@Service("stuUserIndexRepairService")
public class StuUserIndexRepairServiceImpl implements IndexRepairService {
    private final static Log log = LogFactory.getLog(StuUserIndexRepairServiceImpl.class);
	public static final String ID = "id";

	public static final String SURNAME = "surname";
	public static final String MISS_SURNAME = "miss_surname";
	
	public static final String LOCATION = "location_code";

	public static final String LAST_UPDATED_TIME = "last_updated_time"; // 修改时间

	@Override
	public boolean createIndex(Map<String, Object> map) {

		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());

			String path = Lucene.IndexType.stu.getPath();

			indexHolder.add(path, Arrays.asList(StuUserModelFactory.create(map)));

			indexHolder.optimize(path);

			return true;
		} catch (Exception e ) {
		      log.info(e .getMessage());
			e .printStackTrace();
		}

		return false;

	}

	public boolean updateIndex(Map<String, Object> map) {

		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());

			String path = Lucene.IndexType.stu.getPath();

			indexHolder.update(path, Arrays.asList(StuUserModelFactory.create(map)));

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

			String path = Lucene.IndexType.stu.getPath();

			indexHolder.delete(path, Arrays.asList(StuUserModelFactory.create(id)));

			indexHolder.optimize(path);

			return true;
		} catch (Exception e) {
		    log.info(e .getMessage());
			e.printStackTrace();
		}

		return false;
	}

}

