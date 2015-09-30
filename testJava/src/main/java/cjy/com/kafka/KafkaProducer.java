/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月22日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class KafkaProducer {
    public static void main(String[] args) {
        Random rnd = new Random();
        int events = 100;
        // 设置配置属性
        Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // key.serializer.class默认为serializer.class
        props.put("metadata.broker.list", "192.168.2.234:9092,192.168.2.233:9092");
        // 可选配置，如果不配置，则使用默认的partitioner
        // props.put("partitioner.class", "example.producer.SimplePartitioner");
        // 触发acknowledgement机制，否则是fire and forget，可能会引起数据丢失
        // 值为0,1,-1,可以参考
        // http://kafka.apache.org/08/configuration.html
        // props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        // 创建producer
        Producer<String, String> producer = new Producer<String, String>(config);
        // 产生并发送消息
        long start = System.currentTimeMillis();
        for (long i = 0; i < events; i++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + i;// rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;
            // 如果topic不存在，则会自动创建，默认replication-factor为1，partitions为0
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("world", msg);
            producer.send(data);
        }
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        // 关闭producer
        producer.close();
    }
}
