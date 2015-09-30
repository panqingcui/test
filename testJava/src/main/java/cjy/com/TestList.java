/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年11月15日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestList {
    public static void main(String[] args) {
        GNode gNode = new GNode();
        List<GLine> list = new ArrayList<GLine>();
        GLine gline = new GLine();
        gline.setNodeFrom("1");
        gline.setNodeTo("2");
        list.add(gline);
        List<GLine> list2 = new ArrayList<GLine>();
        GLine gline1 = new GLine();
        gline1.setNodeFrom("1");
        gline1.setNodeTo("2");
        list2.add(gline1);
        gNode.setAdjacencies(list2);
        Map<String, GNode> map = new HashMap<String, GNode>();
        map.put("1", gNode);
        List<GLine> list3 = map.get("1").getAdjacencies();
        for (GLine g : list) {
            if (!list3.contains(g)) {
                list3.add(g);
            }
        }
        System.out.println(map.get("1").getAdjacencies().toString());
    }
}
