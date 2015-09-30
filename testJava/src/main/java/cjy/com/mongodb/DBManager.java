/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月27日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class DBManager {
    public static final String DB_NAME = "kaimei";
    public static final String MESSAGE_COLLECTION = "email";

    public static DBManager getInstance() {
        return InnerHolder.INSTANCE;
    }

    /**
     * Creates a new <code>DBManager</code> instance.
     * 
     */
    private DBManager() {}

    private static class InnerHolder {
        static final DBManager INSTANCE = new DBManager();
    }

    public DB getDB() {
        return mongo.getDB(DB_NAME);
    }

    private Mongo mongo;

    public void init(final String ip, int port, int poolSize) throws java.net.UnknownHostException {
        System.setProperty("MONGO.POOLSIZE", String.valueOf(poolSize));
        if (mongo == null) {
            mongo = new Mongo(ip, port);
            MongoOptions options = mongo.getMongoOptions();
            options.autoConnectRetry = true;
            options.connectionsPerHost = poolSize;
        }
    }
}
