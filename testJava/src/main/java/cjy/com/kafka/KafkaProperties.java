/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月22日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public interface KafkaProperties {
    final static String zkConnect = "192.168.2.231:2181";
    final static String groupId = "test-consumer-group";
    final static String topic = "world";
    final static String kafkaServerURL = "192.168.2.233";
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64 * 1024;
    final static int connectionTimeOut = 100000;
    final static int reconnectInterval = 10000;
    final static String topic2 = "topic2";
    final static String topic3 = "topic3";
    final static String clientId = "SimpleConsumerDemoClient";
}
