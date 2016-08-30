/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package com.eureka.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antfact.oplate.config.client.ConfigClientManager;
import com.antfact.oplate.config.client.exception.ConfigerException;
import com.eureka.service.EurekaService;

import util.DBUtil;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2016年1月12日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class ServiceImpl implements EurekaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);

    // private ClientService clientService;
    //
    // public ClientService getClientService() {
    // return clientService;
    // }
    //
    // public void setClientService(ClientService clientService) {
    // DISCOVERY_LOG.error("test client.....");
    // this.clientService = clientService;
    // }
    // public static void main(String[] args) {
    // init();
    // }
    public void init() {
        // "D:" + File.separator + "oplate" + File.separator + "oplate-config" +
        // File.separator + "assemblies" + File.separator + "oplate-config-assembly" +
        // File.separator + "src" + File.separator + "main" + File.separator +
        // "filtered-resources" + File.separator + "etc" + File.separator +
        // "zookeeper.properties"
        //
        // 上传本地文件到远程
        // public static boolean uploadPropertyGroup(String nodeName, String fileName)
        // nodeName 节点名称,fileName本地文件名称
        try {
            ConfigClientManager.getInstance()
                    .uploadPropertyGroup("/antfact/15TqaEat1Aq6bA6C14xJ8E90BwT65ZcD/1.1.0/mysql", "mysql.properties");
        } catch (ConfigerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 加载远程mysql配置与本地配置,优先使用远程配置;动态加载远程配置 loadChangeProperties(String nodePath, String
        // localFileName);nodePath:远程zk节点名称;localFileName：本地文件名,默认加载karaf.home/etc
        try {
            ConfigClientManager.getInstance()
                    .loadChangeProperties("/antfact/15TqaEat1Aq6bA6C14xJ8E90BwT65ZcD/1.1.0/mysql", "mysql.properties");
        } catch (ConfigerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 加载远程配置或本地文件;配置动态加载;如加载远程配置,参数为远程zk节点名称;如加载本地文件,则是本地文件名称
        try {
            ConfigClientManager.getInstance().loadChangeProperties("zookeeper.properties");
        } catch (ConfigerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // // 加载本地文件;非动态加载
        try {
            ConfigClientManager.getInstance().loadProperties("eureka-client.properties");
        } catch (ConfigerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 动态获取值例子参见DBUtil
        while (true) {
            // getPropValue(String propName, String defaultValue)
            // propName:属性名称;defaultValue:默认值
            // 获取属性值
            LOGGER.error("mysql.url:" + ConfigClientManager.getInstance().getPropValue("mysql.url", ""));
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = DBUtil.INSTANCE.getConn().createStatement();
                String sql = "select * from test";
                rs = stmt.executeQuery(sql);
                LOGGER.error("输出结果集：");
                while (rs.next()) {
                    LOGGER.error(rs.getString("name") + "");
                }
            } catch (SQLException e) {
                LOGGER.error("" + e);
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // // clientService.loadChangeProperties(remoteList, list);
        // // String server = clientService.getPropValue("zookeeper.server", "", new
        // // Runnable() {
        // // @Override
        // // public void run() {
        // // DISCOVERY_LOG.error("值发生变化：" +
        // clientService.getPropValue("zookeeper.server",
        // // ""));
        // // }
        // // });
        // String s = ConfigClientManager.getPropValue("zk.port", "", new Runnable() {
        // @Override
        // public void run() {
        // DISCOVERY_LOG.error("值发生变化：" +
        // ConfigClientManager.getPropValue("zookeeper.address", ""));
        // }
        // });
        // // clientService.loadChangeProperties("test.cfg");
        // // ConfigClientManager.loadChangeProperties("test.cfg");
        // // ConfigClientManager.loadProperties("test1.cfg");
        // while (true) {
        // try {
        // Thread.sleep(10000);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // DISCOVERY_LOG.error("zookeeper地址：" +
        // ConfigClientManager.getPropValue("zk.server", ""));
        // DISCOVERY_LOG.error("zookeeper地址：" +
        // ConfigClientManager.getPropValue("zk.port", ""));
        // DISCOVERY_LOG.error("test cfg：" + ConfigClientManager.getPropValue("aa", ""));
        // DISCOVERY_LOG.error("test1 cfg：" + ConfigClientManager.getPropValue("ssss",
        // ""));
        // }
        // clientService.loadProperties("zookeeper.properties");
        // String server1 = clientService.getPropValue("zookeeper.server", "");
        // DISCOVERY_LOG.error("server1:" + server1);
        // DISCOVERY_LOG.error("test config client.....");
        // List<String> remotes = new ArrayList<String>();
        // remotes.add("/rest/config?path=/aa/test");
        // List<String> locals = new ArrayList<String>();
        // locals.add("config-service.properties");
        // ConfigClientManager.install(remotes, locals);
        // System.out.println(ConfigClientManager.getPropValue("config.vipAddress", ""));
        // String parmas = "
        // {'instance':{'instanceId':'logs.service','hostName':'logs.service','app':'Test','ipAddr':'172.19.103.51','status':'UP',
        // 'overriddenstatus':'UNKNOWN','port':{'$':8080,'@enabled':'true'},'securePort':{'$':443,'@enabled':'false'},'countryId':1,'dataCenterInfo':{'@class':'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo','name':'MyOwn'},'leaseInfo':{'renewalIntervalInSecs':30,'durationInSecs':90,'registrationTimestamp':0,'lastRenewalTimestamp':0,'evictionTimestamp':0,'serviceUpTimestamp':0},'metadata':{'@class':'java.util.Collections$EmptyMap'},'appGroupName':'UNKNOWN','homePageUrl':'http://logs.service:8080/','statusPageUrl':'http://logs.service:8080/Status','healthCheckUrl':'http://logs.service:8080/healthcheck','vipAddress':'eureka.mydomain.net','isCoordinatingDiscoveryServer':'false','lastUpdatedTimestamp':'1448614969168','lastDirtyTimestamp':'1448614978863'}}";
        // DISCOVERY_LOG.error(clientService.sendHttpPost("http://172.19.103.51/eureka/v2/apps/Test",
        // parmas));
        // DISCOVERY_LOG.error(clientService.sendHttpPut("http://172.19.103.51/eureka/v2/apps/EUREKA/logs.service",
        // new HashMap<String, String>()));
        // DISCOVERY_LOG.error(clientService.sendHttpGet("http://172.19.103.51/eureka/v2/vips/eureka.mydomain.net"));
    }
}
