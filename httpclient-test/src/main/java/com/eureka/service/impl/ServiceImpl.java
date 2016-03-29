/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package com.eureka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.antfact.oplate.config.client.ConfigClientManager;
import com.eureka.service.EurekaService;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2016年1月12日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class ServiceImpl implements EurekaService {
    private static final Log DISCOVERY_LOG = LogFactory.getLog(ServiceImpl.class);
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

    public void init() {
        List<String> list = new ArrayList<String>();
        list.add("zookeeper.properties");
        List<String> remoteList = new ArrayList<String>();
        remoteList.add("/aa/test");
        ConfigClientManager.loadChangeProperties(remoteList, list);
        // clientService.loadChangeProperties(remoteList, list);
        // String server = clientService.getPropValue("zookeeper.server", "", new
        // Runnable() {
        // @Override
        // public void run() {
        // DISCOVERY_LOG.error("值发生变化：" + clientService.getPropValue("zookeeper.server",
        // ""));
        // }
        // });
        ConfigClientManager.getPropValue("zookeeper.server", "", new Runnable() {
            @Override
            public void run() {
                DISCOVERY_LOG.error("值发生变化：" + ConfigClientManager.getPropValue("zookeeper.server", ""));
            }
        });
        // clientService.loadChangeProperties("test.cfg");
        ConfigClientManager.loadChangeProperties("test.cfg");
        ConfigClientManager.loadProperties("test1.cfg");
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            DISCOVERY_LOG.error("zookeeper地址：" + ConfigClientManager.getPropValue("zookeeper.server", ""));
            DISCOVERY_LOG.error("test cfg：" + ConfigClientManager.getPropValue("aa", ""));
            DISCOVERY_LOG.error("test1 cfg：" + ConfigClientManager.getPropValue("ssss", ""));
        }
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
