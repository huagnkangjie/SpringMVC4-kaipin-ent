package com.kaipin.search.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.search.service.IndexUpdaterService ;

/*
 * 全文检索任务
 * 每天定时重新 创建索引
 */
public class LuceneIndexJobTask implements JobTask {
	private final static Logger LOGGER = LoggerFactory.getLogger(LuceneIndexJobTask.class);

	@Autowired
 
	private IndexUpdaterService  indexUpdaterService;

	@Override
	public void execute() {
		LOGGER.info("lucene task job ");

		indexUpdaterService.start();

	}

}
