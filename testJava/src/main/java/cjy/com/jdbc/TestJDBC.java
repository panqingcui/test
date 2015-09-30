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
package cjy.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>测试JDBC连接是否成功。<p>
 * 
 * 创建日期 2012-10-29<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestJDBC {
    // 日志
    private static final Log logger = LogFactory.getLog(TestJDBC.class);
    // mysql数据库驱动类
    private static final String mysqlDriver = "com.mysql.jdbc.Driver";
    // oracle数据库驱动类
    private static final String oracleDriver = "com.mysql.jdbc.Driver";
    // db2数据库驱动类
    private static final String db2Driver = "com.mysql.jdbc.Driver";
    // sqlserver数据库驱动类
    private static final String sqlserverDriver = "com.mysql.jdbc.Driver";
    // sybase数据库驱动类
    private static final String sybaseDriver = "com.mysql.jdbc.Driver";
    // informix数据库驱动类
    private static final String informixDriver = "com.mysql.jdbc.Driver";
    // postgresql数据库驱动类
    private static final String postgresqlDriver = "com.mysql.jdbc.Driver";

    public static boolean testConnection() {
        boolean isConnection = false;
        String dbName = "mysql";
        String url = "jdbc:mysql://192.168.1.246:3306/antopporter?connectTimeout=1000&socketTimeout=1000";
        String username = "root";
        String password = "antsoldier";
        try {
            if (dbName.equals("mysql")) {
                Class.forName(mysqlDriver).newInstance();
            } else if (dbName.equals("oracle")) {
                Class.forName(oracleDriver).newInstance();
            } else if (dbName.equals("db2")) {
                Class.forName(db2Driver).newInstance();
            } else if (dbName.equals("sqlserver")) {
                Class.forName(sqlserverDriver).newInstance();
            } else if (dbName.equals("sybase")) {
                Class.forName(sybaseDriver).newInstance();
            } else if (dbName.equals("informix")) {
                Class.forName(informixDriver).newInstance();
            } else if (dbName.equals("postgresql")) {
                Class.forName(postgresqlDriver).newInstance();
            } else {
                logger.warn("尚未定义" + dbName + "的数据库驱动！");
            }
        } catch (InstantiationException e) {
            logger.error("实例化异常", e);
        } catch (IllegalAccessException e) {
            logger.error("安全权限异常", e);
        } catch (ClassNotFoundException e) {
            logger.error("没有发现数据库驱动类", e);
        }
        try {
            logger.info("开始测试" + url + "连接");
            Connection connection = DriverManager.getConnection(url, username, password);
            logger.info("完成测试" + url + "连接");
            if (connection == null) {
                logger.warn("不能获取JDBC=" + url + "连接");
            } else {
                logger.info("连接JDBC=" + url + "成功");
                isConnection = true;
            }
        } catch (SQLException e) {
            logger.error("连接数据库" + url + "异常", e);
        } catch (Exception e) {
            logger.error("连接数据库" + url + "异常", e);
        }
        return isConnection;
    }

    public static void main(String[] args) {
        // boolean success = testConnection();
        // if (success) {
        // logger.info("测试数据库连接成功");
        // } else {
        // logger.info("测试数据库连接失败");
        // }
        // -----------------------------------------------------------------------------------------------------------------------------------
        // String mysqlStr =
        // "MySQL_Remote 5.x:mysql:Type=Server:Bytes_sent:jdbcUrl=jdbc%3Amysql%3A//192.168.60.189%3A3306/mysql,jdbcUser=root,jdbcPassword=antsoldier__RATE__=1m";
        // logger.info("mysqlStr=" + mysqlStr);
        // -----------------------------------------------------------------------------------------------------------------------------------
        String jmxStr = "Apache Tomcat 6.0:java.lang:type=Memory:Composite.HeapMemoryUsage.committed:"
                + "jmx.url=service%3Ajmx%3Armi%3A///jndi/rmi%3A//192.168.1.18%3A6962/jmxrmi,jmx.username=,jmx.password=manager__RATE__=1m";
        logger.info("jmxStr=" + jmxStr);
        // -----------------------------------------------------------------------------------------------------------------------------------
        // String snmpStr =
        // "FileServer Mount_Remote:remote:Type=Server,snmpVersion=v1,"
        // +
        // "snmpIp=192.168.60.249,snmpPort=161,snmpCommunity=public,diskID=7:MountFree";
        // String snmpStr = "NetworkServer Interface_Remote:remote:"
        // +
        // "Type=Server,snmpVersion=v1,snmpIp=192.168.1.248,snmpPort=161,snmpCommunity=public,netInterfaceID=11:BytesIn__RATE__=1m";
        // logger.info("snmpStr=" + snmpStr);
        // -----------------------------------------------------------------------------------------------------------------------------------
        // String dbName = "";
        // String url = "";
        // String user = "";
        // String password = "";
        // if (mysqlStr.contains("__RATE__")) {
        // mysqlStr = mysqlStr.substring(0, mysqlStr.indexOf("__RATE__"));
        // }
        // String[] mysqlStrArr = mysqlStr.split(":");
        // dbName = mysqlStrArr[1];
        // String dbProperty = mysqlStrArr[mysqlStrArr.length - 1];
        // String[] dbPropertyArr = dbProperty.split(",");
        // for (String str : dbPropertyArr) {
        // String[] propertyArr = str.split("=");
        // if (propertyArr[0].equals("jdbcUrl")) {
        // url = propertyArr[1].replaceAll("%3A", ":") +
        // "?connectTimeout=1000&socketTimeout=1000";
        // } else if (propertyArr[0].equals("jdbcUser")) {
        // user = propertyArr[1];
        // } else if (propertyArr[0].equals("jdbcPassword")) {
        // password = propertyArr[1];
        // }
        // }
        // logger.info("dbName=" + dbName + ", url=" + url + ", user=" + user +
        // ", password=" + password);
        // -----------------------------------------------------------------------------------------------------------------------------------
        logger.info("----------------------------------------------------------------------------------------------------------------------------");
        String jmxUrl = "";
        String jmxUserName = "";
        String jmxPassword = "";
        if (jmxStr.contains("__RATE__")) {
            jmxStr = jmxStr.substring(0, jmxStr.indexOf("__RATE__"));
        }
        String[] jmxStrArr = jmxStr.split(":");
        for (String str : jmxStrArr) {
            logger.info("str=" + str);
        }
        logger.info("----------------------------------------------------------------------------------------------------------------------------");
        String jmxProperty = jmxStrArr[jmxStrArr.length - 1];
        String[] jmxPropertyArr = jmxProperty.split(",");
        for (String str : jmxPropertyArr) {
            logger.info("str=" + str);
        }
        logger.info("----------------------------------------------------------------------------------------------------------------------------");
        for (String str : jmxPropertyArr) {
            String[] propertyArr = str.split("=");
            if (propertyArr.length > 1) {
                if (propertyArr[0].equals("jmx.url")) {
                    jmxUrl = propertyArr[1].replaceAll("%3A", ":");
                } else if (propertyArr[0].equals("jmx.username")) {
                    jmxUserName = propertyArr[1];
                } else if (propertyArr[0].equals("jmx.password")) {
                    jmxPassword = propertyArr[1];
                }
            }
        }
        logger.info("jmxUrl=" + jmxUrl + ", jmxUserName=" + jmxUserName + ", jmxPassword=" + jmxPassword);
        // -----------------------------------------------------------------------------------------------------------------------------------
        // logger.info("----------------------------------------------------------------------------------------------------------------------------");
        // String snmpVersion = "";
        // String snmpIp = "";
        // String snmpPort = "";
        // String snmpCommunity = "";
        // String[] snmpStrArr = snmpStr.split(":");
        // for (String str : snmpStrArr) {
        // logger.info("str=" + str);
        // }
        // logger.info("----------------------------------------------------------------------------------------------------------------------------");
        // String snmpProperty = snmpStrArr[snmpStrArr.length - 2];
        // logger.info("snmpProperty=" + snmpProperty);
        // String[] jmxPropertyArr = snmpProperty.split(",");
        // for (String str : jmxPropertyArr) {
        // logger.info("str=" + str);
        // }
        // logger.info("----------------------------------------------------------------------------------------------------------------------------");
        // for (String str : jmxPropertyArr) {
        // String[] propertyArr = str.split("=");
        // if (propertyArr[0].equals("snmpVersion")) {
        // snmpVersion = propertyArr[1];
        // } else if (propertyArr[0].equals("snmpIp")) {
        // snmpIp = propertyArr[1];
        // } else if (propertyArr[0].equals("snmpPort")) {
        // snmpPort = propertyArr[1];
        // } else if (propertyArr[0].equals("snmpCommunity")) {
        // snmpCommunity = propertyArr[1];
        // }
        // }
        // logger.info("snmpVersion=" + snmpVersion + ", snmpIp=" + snmpIp +
        // ", snmpPort=" + snmpPort + ", snmpCommunity="
        // + snmpCommunity);
        // -----------------------------------------------------------------------------------------------------------------------------------
    }
}
