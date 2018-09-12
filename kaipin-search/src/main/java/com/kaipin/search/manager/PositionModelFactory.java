package com.kaipin.search.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.service.impl.PositionIndexRepairServiceImpl;

public class PositionModelFactory {

 
	

	/**
	 * 外部数据格式转化为待存储的格式
	 * 
	 * @param map
	 * @return
	 */

	public static  Map<String, Object> api2Store(Map map) {

		Map<String, Object> rmap = new HashMap<>();

		rmap.put(Position.ID, MapUtils.getString(map, PositionIndexRepairServiceImpl.ID));

		rmap.put(Position.TITLE, MapUtils.getString(map, PositionIndexRepairServiceImpl.POSITION_NAME));

		rmap.put(Position.LOCATION, MapUtils.getString(map, PositionIndexRepairServiceImpl.LOCATION_CODE));

		rmap.put(Position.INDUSTRY, MapUtils.getString(map, PositionIndexRepairServiceImpl.INDUSTRY_CODE));

		rmap.put(Position.TIME, MapUtils.getString(map, PositionIndexRepairServiceImpl.LAST_UPDATED_TIME));

		
		return rmap;

	}

	public static  Map<String, Object>db2Store(Map map) {

		return api2Store(map);

	}
	
	
	
	
	
	
	
	
	public static Position create (String id) {

		Position position = new Position(id, null, null);

		return position;
	}

	public static Position create (Map<String, Object> content) {

		try {

			String id = MapUtils.getString(content, Position.ID);

			String title = MapUtils.getString(content, Position.TITLE);

			Map<String, String> mapExtendStoreDatas = new HashMap<>();

			mapExtendStoreDatas.put(Position.INDUSTRY, MapUtils.getString(content, Position.INDUSTRY));

			mapExtendStoreDatas.put(Position.TIME, MapUtils.getString(content, Position.TIME));

			mapExtendStoreDatas.put(Position.LOCATION, MapUtils.getString(content, Position.LOCATION));

			Position position = new Position(id, title, mapExtendStoreDatas);

			return position;
		} catch (Exception e) {

		}
		return null;
	}
}
