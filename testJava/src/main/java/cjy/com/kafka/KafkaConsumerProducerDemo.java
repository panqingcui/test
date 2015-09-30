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
public class KafkaConsumerProducerDemo implements KafkaProperties {
    public static void main(String[] args) {
        // Producer producerThread = new Producer(KafkaProperties.topic);
        // producerThread.start();
        Consumer consumerThread = new Consumer("kafka");
        consumerThread.start();
    }
}
