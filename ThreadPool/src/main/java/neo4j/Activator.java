/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package neo4j;

import java.io.File;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.rest.graphdb.RestGraphDatabase;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-4-26<br>
 * @author liwenjun(liwenjun@antvision.cn)<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Activator {
    private static final Log m_log = LogFactory.getLog(Activator.class);
    // neo4j服务
    public static GraphDatabaseService db;
    // 数据库路径
    private static final String DB_PATH = System.getProperty("loong.home") + File.separator + "neo4j-db";
    // 配置文件路径
    private static final String CONFIGFILE = System.getProperty("loong.home") + "/conf/antsoldier.neo4j.properties";
    // 服务器地址
    private static final String REDIS_SERVER_URL = "neo4j.server.url";
    // 配置项
    private Properties properties;

    public GraphDatabaseService getDb() {
        db = new RestGraphDatabase("http://localhost:7474/db/");
        registerShutdownHook(db);
        return db;
    }

    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                graphDb.shutdown();
            }
        });
    }
}
