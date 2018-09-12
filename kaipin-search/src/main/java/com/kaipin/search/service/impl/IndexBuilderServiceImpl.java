package com.kaipin.search.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kaipin.search.common.page.IGenericPage;
import com.kaipin.search.constant.Lucene;
import com.kaipin.search.constant.Lucene.IndexType;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.core.index.Searchable;
import com.kaipin.search.manager.CompanyModelFactory;
import com.kaipin.search.manager.LiveModelFactory;
import com.kaipin.search.manager.PositionModelFactory;
import com.kaipin.search.manager.SchInfoModelFactory;
import com.kaipin.search.manager.StuUserModelFactory;
import com.kaipin.search.model.OutPacket;
import com.kaipin.search.repository.dao.IndexBuildDao;
import com.kaipin.search.service.IndexBuilderService;
import com.kaipin.search.service.LuceneIndexFactory;
import com.kaipin.search.util.FileUtils;

@Service("indexBuilderService")
public class IndexBuilderServiceImpl implements IndexBuilderService {

	private final static Logger LOGGER = LoggerFactory.getLogger(IndexBuilderServiceImpl.class);

	// @Autowired
	// private LuceneIndexFactory luceneIndexFactory;

	@Resource(name = "positionInfoDao")
	
	private IndexBuildDao positionInfoDao;

	@Resource(name = "liveInfoDao")
	private IndexBuildDao liveInfoDao;

	@Resource(name = "companyInfoDao")
	private IndexBuildDao companyInfoDao;
	
	//@Resource(name = "stuUserBuildDao")
	@Autowired
	@Qualifier("stuUserBuildDao")
	private IndexBuildDao stuUserDao;
	
	@Resource(name = "schInfoDao")
	private IndexBuildDao schInfoDao;

	public IndexCount reBuildAll() throws IOException {
		// FileUtils.deleteAllFilesOfDir(new File(Lucene.getLuceneDir() +
		// Lucene.IndexType.index.getPath()));

		FileUtils.deleteFile(new File(Lucene.getPositionPath()));

		FileUtils.deleteFile(new File(Lucene.getCompanyPath()));

		FileUtils.deleteFile(new File(Lucene.getLivePath()));
		
		FileUtils.deleteFile(new File(Lucene.getStuPath()));
		
		FileUtils.deleteFile(new File(Lucene.getSchPath()));

		// return null;
		return buildAll();
	}

	@Override
	public IndexCount buildAll() throws IOException {
		IndexCount c = new IndexCount();
		c.live = buildLive();

		c.company = buildCompany();

		c.position = buildPosition();
		
		c.stu = buildStuUser();
		
		c.sch = buildSchInfo();

		return c;
	}

	@Override
	public int buildPosition() throws IOException {

		int pageNo = 1;

		int pageSize = Lucene.DEFAULT_COUNT;
		int ic = 0;
		Map<String, Object> mapsearch = new HashMap<>();

		IGenericPage page;

		List<Searchable> listPosition = new ArrayList<>();

		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		while (true) {

			page = positionInfoDao.getSearchAll(mapsearch, pageNo, pageSize);

			if (page.getPageElementCount() > 0) {

				List<Map<String, Object>> list = page.getThisPageElements();

				for (Map<String, Object> content : list) {

					try {

						listPosition.add(PositionModelFactory.create(PositionModelFactory.db2Store(content)));
				        ic++;
					} catch (Exception e) {

					}

				}

				if (indexHolder != null) {

					addDocument(listPosition, indexHolder, IndexType.position.getPath());

					listPosition.clear();
				}

			} else {

				break;

			}

			if (page.hasNextPage()) {

				pageNo++;
			} else {

				break;
			}

		}
		return ic;
	}

	private int addDocument(List<Searchable> listPosition, IndexHolder indexHolder, String path) {

		try {
			int ic = indexHolder.add(path, listPosition);

			indexHolder.optimize(path);

			return ic;

		} catch (Exception e) {

		}
		return 0;
	}

	@Override
	public int buildCompany() throws IOException {

		int pageNo = 1;

		int pageSize = Lucene.DEFAULT_COUNT;
		int ic = 0;
		Map<String, Object> mapsearch = new HashMap<>();

		IGenericPage page;

		List<Searchable> lists = new ArrayList<>();

		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		while (true) {

			page = companyInfoDao.getSearchAll(mapsearch, pageNo, pageSize);

			if (page.getPageElementCount() > 0) {

				List<Map<String, Object>> list = page.getThisPageElements();

				for (Map<String, Object> content : list) {

					try {

						lists.add(CompanyModelFactory.create(CompanyModelFactory.db2Store(content)));
				        ic++;
					} catch (Exception e) {

					}

				}

				if (indexHolder != null) {

					addDocument(lists, indexHolder, IndexType.company.getPath());

					lists.clear();
				}

			} else {

				break;

			}
		   
			if (page.hasNextPage()) {

				pageNo++;
			} else {

				break;
			}

		}
		return ic;

	}

	@Override
	public int buildLive() throws IOException {

		int pageNo = 1;

		int pageSize = Lucene.DEFAULT_COUNT;

		int ic = 0;

		Map<String, Object> mapsearch = new HashMap<>();

		IGenericPage page;

		List<Searchable> lists = new ArrayList<>();

		IndexHolder indexHolder = null;

		try {

			indexHolder = IndexHolder.init(Lucene.getLuceneDir());

		} catch (IOException e1) {

			e1.printStackTrace();
		}

		while (true) {

			page = liveInfoDao.getSearchAll(mapsearch, pageNo, pageSize);

			if (page.getPageElementCount() > 0) {

				List<Map<String, Object>> list = page.getThisPageElements();

				for (Map<String, Object> content : list) {

					try {

						lists.add(LiveModelFactory.create(LiveModelFactory.db2Store(content)));
						ic++;
					} catch (Exception e) {

					}

				}

				if (indexHolder != null) {

					addDocument(lists, indexHolder, IndexType.live.getPath());

					lists.clear();
				}

			} else {

				break;

			}

			if (page.hasNextPage()) {

				pageNo++;
			} else {

				break;
			}

		}
		return ic;

	}

	public static class IndexCount  extends OutPacket {
		public int company;
		public int live;
		public int position;
		public int stu;
		public int sch;
	}

	@Override
	public int buildStuUser() throws IOException {
		int pageNo = 1;
		int pageSize = Lucene.DEFAULT_COUNT;
		int ic = 0;
		
		Map<String, Object> mapsearch = new HashMap<>();
		IGenericPage page;
		List<Searchable> lists = new ArrayList<>();
		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		while (true) {
			page = stuUserDao.getSearchAll(mapsearch, pageNo, pageSize); 
			if (page.getPageElementCount() > 0) {
				List<Map<String, Object>> list = page.getThisPageElements();
				for (Map<String, Object> content : list) {
					try {
						lists.add(StuUserModelFactory.create(StuUserModelFactory.db2Store(content)));
						ic++;
						} catch (Exception e) {
					}
				}
				if (indexHolder != null) {
					addDocument(lists, indexHolder, IndexType.stu.getPath());
					lists.clear();
				}
			} else {
				break;
			}
			if (page.hasNextPage()) {
				pageNo++;
			} else {
				break;
			}
		}
		return ic;
	}

	@Override
	public int buildSchInfo() throws IOException {
		int pageNo = 1;
		int pageSize = Lucene.DEFAULT_COUNT;
		int ic = 0;
		
		Map<String, Object> mapsearch = new HashMap<>();
		IGenericPage page;
		List<Searchable> lists = new ArrayList<>();
		IndexHolder indexHolder = null;

		try {
			indexHolder = IndexHolder.init(Lucene.getLuceneDir());
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		while (true) {
			page = schInfoDao.getSearchAll(mapsearch, pageNo, pageSize); 
			if (page.getPageElementCount() > 0) {
				List<Map<String, Object>> list = page.getThisPageElements();
				for (Map<String, Object> content : list) {
					try {
						lists.add(SchInfoModelFactory.create(SchInfoModelFactory.db2Store(content)));
					      ic++;
						} catch (Exception e) {
					}
				}
				if (indexHolder != null) {
					addDocument(lists, indexHolder, IndexType.sch.getPath());
					lists.clear();
				}
			} else {
				break;
			}
			if (page.hasNextPage()) {
				pageNo++;
			} else {
				break;
			}
		}
		return ic;
	}

}
