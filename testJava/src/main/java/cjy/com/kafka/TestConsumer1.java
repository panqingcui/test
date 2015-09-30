/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import com.alibaba.fastjson.JSON;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月26日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestConsumer1 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("zookeeper.connect", KafkaProperties.zkConnect);
        props.put("group.id", "groupId");
        props.put("zookeeper.session.timeout.ms", "60000");
        props.put("rebalance.backoff.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        ConsumerConfig config = new ConsumerConfig(props);
        kafka.javaapi.consumer.ConsumerConnector connector = kafka.consumer.Consumer
                .createJavaConsumerConnector(config);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("wl", new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> map = connector.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = map.get("wl").get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext())
            System.out.println((JSON.parseObject(new String(it.next().message()), KeyWord.class)));
    }
}
