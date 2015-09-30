/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2013-2014 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

import java.util.Properties;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月24日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class UserProducer {
    public static void main(String[] args) {
        Properties pro = new Properties();
        pro.put("metadata.broker.list", "192.168.2.233:9092");
        // pro.put("key.serializer.class", "kafka.serializer.StringEncoder");
        pro.put("serializer.class", "cjy.com.kafka.UserEncoder");
        pro.put("partitioner.class", "cjy.com.kafka.TestPartition");
        pro.put("request.required.acks", "1");
        kafka.javaapi.producer.Producer<byte[], byte[]> producer = new kafka.javaapi.producer.Producer<byte[], byte[]>(
                new ProducerConfig(pro));
        int i = 0;
        while (true) {
            KeyWord msg = new KeyWord();
            msg.setAddress("earth");
            msg.setContent("test" + i);
            KeyedMessage keydMessage = new KeyedMessage("wl", msg, msg);
            producer.send(keydMessage);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        // producer.close();
    }
}
