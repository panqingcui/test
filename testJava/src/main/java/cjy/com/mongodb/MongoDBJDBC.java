/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月23日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class MongoDBJDBC {
    public static void main(String args[]) throws UnknownHostException {
        Mongo mg = new Mongo("192.168.2.107", 27017);
        DB db = mg.getDB("myinfo");
        boolean auth = db.authenticate("sa", "sa".toCharArray());
        System.out.println(auth);
        // db = mg.getDB("test");
        // // 查询所有的Database
        // for (String name : mg.getDatabaseNames()) {
        // System.out.println("dbName: " + name);
        // }
        // if (auth) {
        // // 查询所有的聚集集合
        // for (String name : db.getCollectionNames()) {
        // System.out.println("collectionName: " + name);
        // // 集合中对应的数据
        // DBCollection users = db.getCollection(name);
        // // 查询所有的数据
        // DBCursor cur = users.find();
        // while (cur.hasNext()) {
        // System.out.println(JSON.parse(cur.next().toString()));
        // }
        // System.out.println(cur.count());
        // System.out.println(cur.getCursorId());
        // // System.out.println(JSON.serialize(cur));
        // }
        // }
        // DBCollection user = db.getCollection("userdetails");
        // DBCursor cur = user.find();
        // while (cur.hasNext()) {
        // System.out.println(JSON.parse(cur.next().toString()));
        // }
    }
}
