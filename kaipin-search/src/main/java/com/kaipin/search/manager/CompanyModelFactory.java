package com.kaipin.search.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.kaipin.search.core.dimension.Company;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.service.impl.CompanyIndexRepairServiceImpl;

public class CompanyModelFactory {

	
	

	public  static Map<String, Object> api2Store(Map map) {

		Map<String, Object> rmap = new HashMap<>();

		rmap.put(Position.ID, MapUtils.getString(map, CompanyIndexRepairServiceImpl.ID));

		rmap.put(Position.TITLE, MapUtils.getString(map, CompanyIndexRepairServiceImpl.ENT_NAME));

		rmap.put(Position.LOCATION, MapUtils.getString(map, CompanyIndexRepairServiceImpl.OFFICE_AREA));

		rmap.put(Position.INDUSTRY, MapUtils.getString(map, CompanyIndexRepairServiceImpl.INDUSTRY_CODE));

		rmap.put(Position.TIME, MapUtils.getString(map, CompanyIndexRepairServiceImpl.LAST_UPDATED_TIME));

		return rmap;

	}

	public static Map<String, Object> db2Store(Map map) {

		return api2Store(map);

	}

	
	
	
	
	
	
	
	
	public static Company create (String id) {

		Company obj = new Company(id, null, null);

		return obj;
	}

	public static Company create (Map<String, Object> content) {

		try {

			String id = MapUtils.getString(content, Company.ID);

			String title = MapUtils.getString(content, Company.TITLE);

			Map<String, String> mapExtendStoreDatas = new HashMap<>();

			mapExtendStoreDatas.put(Position.INDUSTRY, MapUtils.getString(content, Company.INDUSTRY));

			mapExtendStoreDatas.put(Position.TIME, MapUtils.getString(content, Company.TIME));

			mapExtendStoreDatas.put(Position.LOCATION, MapUtils.getString(content, Company.LOCATION));

			Company obj = new Company(id, title, mapExtendStoreDatas);

			return obj;

		} catch (Exception e) {

		}
		return null;

	}

}
