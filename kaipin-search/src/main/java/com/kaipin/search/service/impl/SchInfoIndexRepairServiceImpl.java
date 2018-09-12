package com.kaipin.search.service.impl;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.kaipin.search.constant.Lucene;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.manager.SchInfoModelFactory;
import com.kaipin.search.manager.SchInfoModelFactory;
import com.kaipin.search.service.IndexRepairService;

@Service("schInfoIndexRepariService")
public class SchInfoIndexRepairServiceImpl implements IndexRepairService {
    private final static Log log = LogFactory.getLog(SchInfoIndexRepairServiceImpl.class);
	public static final String ID = "id";
	public static final String SCHOOL_NAME = "school_name";
	public static final String SCHOOL_TYPE_ID = "school_type_id";
	public static final String SCHOOL_NATURE_ID = "school_nature_id";
	public static final String SCHOOL_FEATURE_ID = "school_feature_id";
	public static final String SCHOOL_CLASS_ID = "school_class_id";
	public static final String SCHOOL_SHORT_NAME = "school_short_name";
	public static final String SCHOOL_LOGO = "school_logo";
	public static final String LOCATION_CODE = "location_code";
	public static final String CREATE_TIME = "create_time";
	public static final String WEBSITE = "website";

	@Override
	public boolean createIndex(Map<String, Object> map) {

		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());

			String path = Lucene.IndexType.sch.getPath();

			indexHolder.add(path, Arrays.asList(SchInfoModelFactory.create(map)));

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

			String path = Lucene.IndexType.sch.getPath();

			indexHolder.update(path, Arrays.asList(SchInfoModelFactory.create(map)));

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

			String path = Lucene.IndexType.sch.getPath();

			indexHolder.delete(path, Arrays.asList(SchInfoModelFactory.create(id)));

			indexHolder.optimize(path);

			return true;
		} catch (Exception e) {
		      log.info(e .getMessage());
			e.printStackTrace();
		}

		return false;
	}

}

