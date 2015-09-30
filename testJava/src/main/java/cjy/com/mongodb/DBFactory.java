/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月27日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class DBFactory {
    public static MongoDBManager getMongoDB() {
        return MongoDBManager.getInstance();
    }

    public static MongoDBManager getMongoDB(String dbName) {
        return MongoDBManager.getInstance(dbName);
    }
}
