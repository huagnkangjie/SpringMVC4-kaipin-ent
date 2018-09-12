package com.kaipin.search.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.kaipin.search.core.dimension.Live;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.core.dimension.StuUser;
import com.kaipin.search.service.impl.StuUserIndexRepairServiceImpl;
import com.kaipin.search.util.DateFormatUtils;
import com.kaipin.search.util.StringUtil;

public class StuUserModelFactory {

    public static Map<String, Object> api2Store(Map map) {

        Map<String, Object> rmap = new HashMap<>();

        rmap.put(Position.ID, MapUtils.getString(map, StuUserIndexRepairServiceImpl.ID));

        String name = getStrings(MapUtils.getString(map, StuUserIndexRepairServiceImpl.SURNAME))
                + getStrings(MapUtils.getString(map, StuUserIndexRepairServiceImpl.MISS_SURNAME));

        rmap.put(Position.TITLE, name);
        rmap.put(Position.LOCATION,
                MapUtils.getString(map, StuUserIndexRepairServiceImpl.LOCATION));
        rmap.put(Position.INDUSTRY, MapUtils.getString(map, null));
        rmap.put(Position.TIME,
                MapUtils.getString(map, StuUserIndexRepairServiceImpl.LAST_UPDATED_TIME));
        return rmap;

    }


    public static Map<String, Object> db2Store(Map map) {

        return api2Store(map);

    }

    public static StuUser create(String id) {

        StuUser stu = new StuUser(id, null, null);

        return stu;
    }

    public static StuUser create(Map<String, Object> content) {

        try {

            String id = MapUtils.getString(content, Position.ID);

            String title = MapUtils.getString(content, Position.TITLE);

            Map<String, String> mapExtendStoreDatas = new HashMap<>();

            mapExtendStoreDatas.put(Position.INDUSTRY,
                    MapUtils.getString(content, Position.INDUSTRY));

            mapExtendStoreDatas.put(Position.TIME, MapUtils.getString(content, Position.TIME));

            mapExtendStoreDatas.put(Position.LOCATION,
                    MapUtils.getString(content, Position.LOCATION));

            StuUser stu = new StuUser(id, title, mapExtendStoreDatas);

            return stu;

        } catch (Exception e) {

        }
        return null;
    }

    public static String getStrings(String str) {
        if (StringUtil.isEmpty(str)) {
            str = "";
        }
        return str;

    }
}

