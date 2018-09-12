package com.kaipin.task.service.web.live;

import java.util.List;
import java.util.Map;

public interface ILiveTipService {
	/**
	 * 获取直播需要推送的人员
	 * @return
	 */
	public List<Map<String, Object>> getLiveTipList();
	
	/**
	 * 添加记录
	 * @param map
	 */
	public void insertPhoneTip(Map<String,Object> map);
}
