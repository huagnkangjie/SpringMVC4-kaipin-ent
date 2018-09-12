package com.kaipin.task.repository.dao.web.live;

import java.util.List;
import java.util.Map;

public interface ILiveTipDao {

	/**
	 * 获取直播需要推送的人员
	 * @return
	 */
	List<Map<String, Object>> getLiveTipList();
	
	void insertPhoneTip(Map<String,Object> map);
	
	
}
