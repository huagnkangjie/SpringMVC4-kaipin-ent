package com.kaipin.search.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.core.dimension.SchInfo;
import com.kaipin.search.core.dimension.StuUser;
import com.kaipin.search.service.impl.SchInfoIndexRepairServiceImpl;
import com.kaipin.search.service.impl.StuUserIndexRepairServiceImpl;

public class SchInfoModelFactory {
	
	public static Map<String, Object> api2Store(Map map) {

		Map<String, Object> rmap = new HashMap<>();

		rmap.put(SchInfo.ID, MapUtils.getString(map, SchInfoIndexRepairServiceImpl.ID));
		
		rmap.put(SchInfo.TITLE, MapUtils.getString(map, SchInfoIndexRepairServiceImpl.SCHOOL_NAME));

		rmap.put(SchInfo.LOCATION, MapUtils.getString(map, SchInfoIndexRepairServiceImpl.LOCATION_CODE));

		rmap.put(SchInfo.INDUSTRY, MapUtils.getString(map, null));

		
		rmap.put(SchInfo.TIME, MapUtils.getString(map, SchInfoIndexRepairServiceImpl.CREATE_TIME));

		return rmap;

	}
	

	public  static Map<String, Object> db2Store(Map map) {

		return api2Store(map);

	}
	
	public static SchInfo create(String id) {

		SchInfo sch = new SchInfo(id, null, null);

		return sch;
	}

	public static SchInfo create(Map<String, Object> content) {

		try {

			String id = MapUtils.getString(content, SchInfo.ID);

			String title = MapUtils.getString(content, SchInfo.TITLE);

			Map<String, String> mapExtendStoreDatas = new HashMap<>();

			mapExtendStoreDatas.put(SchInfo.INDUSTRY, MapUtils.getString(content, SchInfo.INDUSTRY));

			mapExtendStoreDatas.put(SchInfo.TIME, MapUtils.getString(content, SchInfo.TIME));

			mapExtendStoreDatas.put(SchInfo.LOCATION, MapUtils.getString(content, SchInfo.LOCATION));

			SchInfo  sch = new SchInfo(id, title, mapExtendStoreDatas);

			return sch;
			
		} catch (Exception e) {

		}
		return null;
	}
}

