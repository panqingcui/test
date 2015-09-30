/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package cjy.com.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>map测试类。<p>
 * 
 * 创建日期 2012-11-8<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestMap {
    // 日志
    private static final Log logger = LogFactory.getLog(TestMap.class);

    public static void main(String[] args) {
        // 添加一个新Map
        Map<MapObject, List<String>> objMap = new HashMap<MapObject, List<String>>();
        String mapName1 = "map1";
        MapObject map1 = new MapObject(mapName1);
        List<String> mapStrList1 = new ArrayList<String>();
        mapStrList1.add("map string 1-1");
        mapStrList1.add("map string 1-2");
        objMap.put(map1, mapStrList1);
        // 遍历Map
        printMap(objMap);
        // 测试Map
        logger.info("-----------------------------------------------------------------");
        String mapName2 = "map1";
        MapObject map2 = new MapObject(mapName2);
        String mapStr2 = "map string 2-1";
        if (map2.equals(map1)) {
            logger.info("map2 equals map1");
        }
        boolean flag = false;
        Iterator<Entry<MapObject, List<String>>> iter = objMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();
            MapObject map = (MapObject) entry.getKey();
            if (map.equals(map2)) {
                flag = true;
                @SuppressWarnings("unchecked")
                List<String> mapStrList = (List<String>) entry.getValue();
                mapStrList.add(mapStr2);
                break;
            }
        }
        if (!flag) {
            List<String> mapStrList2 = new ArrayList<String>();
            mapStrList2.add(mapStr2);
            objMap.put(map2, mapStrList2);
        }
        // 遍历Map
        printMap(objMap);
        // 测试map键值
        logger.info("-----------------------------------------------------------------");
        Map<String, String> strMap = new HashMap<String, String>();
        String str1 = "str1";
        String str2 = "str1";
        strMap.put(str1, "str value 1-1");
        String value = strMap.get(str2);
        if (value == null) {
            logger.info("value is null");
        } else {
            logger.info("value=" + value);
        }
    }

    /**
     * 遍历Map
     * @param objMap
     */
    private static void printMap(Map<MapObject, List<String>> objMap) {
        logger.info("map大小为:" + objMap.size());
        Iterator<Entry<MapObject, List<String>>> iter = objMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();
            MapObject map = (MapObject) entry.getKey();
            @SuppressWarnings("unchecked")
            List<String> mapStrList = (List<String>) entry.getValue();
            logger.info("mapName=" + map.getName());
            for (String str : mapStrList) {
                logger.info("str=" + str);
            }
        }
    }
}
