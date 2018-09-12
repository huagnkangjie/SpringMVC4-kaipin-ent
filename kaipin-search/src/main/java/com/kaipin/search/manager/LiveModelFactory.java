package com.kaipin.search.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.kaipin.search.core.dimension.Live;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.service.impl.LiveIndexRepairServiceImpl;
import com.kaipin.search.service.impl.PositionIndexRepairServiceImpl;
import com.kaipin.search.util.DateFormatUtils;

public class LiveModelFactory {

	
	
	
	
	
	
	
	
	public static Map<String, Object> api2Store(Map map) {

		Map<String, Object> rmap = new HashMap<>();

		rmap.put(Position.ID, MapUtils.getString(map, LiveIndexRepairServiceImpl.ID));

		rmap.put(Position.TITLE, MapUtils.getString(map, LiveIndexRepairServiceImpl.SUBJECT));

		rmap.put(Position.LOCATION, MapUtils.getString(map, LiveIndexRepairServiceImpl.OFFICE_AREA));

		rmap.put(Position.INDUSTRY, MapUtils.getString(map, LiveIndexRepairServiceImpl.INDUSTRY_CODE));

		
		//yyyy-mm-dd hms 转换为时间戳 统一排序用
		rmap.put(Position.TIME, DateFormatUtils.getTime(MapUtils.getString(map, LiveIndexRepairServiceImpl.START_TIME)));

		return rmap;

	}
	
	
	

	public  static Map<String, Object> db2Store(Map map) {

		return api2Store(map);

	}
	
	
	
	
	
	
	public static Live create(String id) {

		Live live = new Live(id, null, null);

		return live;
	}

	public static Live create(Map<String, Object> content) {

		try {

			String id = MapUtils.getString(content, Position.ID);

			String title = MapUtils.getString(content, Position.TITLE);

			Map<String, String> mapExtendStoreDatas = new HashMap<>();

			mapExtendStoreDatas.put(Position.INDUSTRY, MapUtils.getString(content, Position.INDUSTRY));

			mapExtendStoreDatas.put(Position.TIME, MapUtils.getString(content, Position.TIME));

			mapExtendStoreDatas.put(Position.LOCATION, MapUtils.getString(content, Position.LOCATION));

			Live  live = new Live(id, title, mapExtendStoreDatas);

			return live;
			
		} catch (Exception e) {

		}
		return null;
	}
}
