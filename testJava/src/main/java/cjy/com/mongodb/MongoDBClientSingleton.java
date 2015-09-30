/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

/**
 * <p>mongodb连接单例。<p>
 * 
 * 创建日期 2014年10月27日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public enum MongoDBClientSingleton {
    INSTANCE;
    private DB db;
    private Logger logger = Logger.getLogger(MongoDBClientSingleton.class);

    @SuppressWarnings("deprecation")
    MongoDBClientSingleton() {
        try {
            // 地址 端口号 本身就是连接池，且线程安全
            Mongo mongoDB = new Mongo("192.168.2.107", 27017);
            // 设置连接参数
            MongoOptions opt = mongoDB.getMongoOptions();
            opt.setConnectTimeout(1000);
            opt.setMaxWaitTime(1000);
            opt.setSocketTimeout(1000);
            opt.setConnectionsPerHost(30);
            db = mongoDB.getDB("myinfo");
            db.authenticate("sa", "sa".toCharArray());
        } catch (UnknownHostException e) {
            logger.error(e);
        } catch (Throwable e) {
            logger.error(e);
        }
    }

    public DB getDB() {
        return db;
    }

    public DBCollection getCollection(String colName) {
        return getDB().getCollection(colName);
    }
}
