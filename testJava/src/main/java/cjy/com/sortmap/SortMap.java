/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package cjy.com.sortmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年9月11日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class SortMap {
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Map<String, Integer> myMap = new LinkedHashMap();
        myMap.put("1", 1);
        myMap.put("2", 4);
        myMap.put("3", 3);
        myMap.put("4", 9);
        myMap.put("5", 6);
        myMap.put("6", 2);
        printMap(myMap);
        myMap = sortMap(myMap);
        printMap(myMap);
    }

    private static void printMap(Map map) {
        System.out.println("===================mapStart==================");
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("===================mapEnd==================");
    }

    public static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Entry<java.lang.String, Integer> arg0, Entry<java.lang.String, Integer> arg1) {
                return arg0.getValue() - arg1.getValue();
            }
        });
        Map newMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }
}
