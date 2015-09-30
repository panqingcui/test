/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月23日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Test {
    public static void main(String[] args) {
        // Properties props = new Properties();
        // props.put("zk.connect", "192.168.10.11:2181");
        // /** 选择用哪个类来进行序列化 */
        // props.put("serializer.class", "org.gfg.kafka.message.KeywordMessage");
        // props.put("zk.connectiontimeout.ms", "6000");
        // ProducerConfig config = new ProducerConfig(props);
        // /** 制造数据 */
        // KeyWord keyword = new KeyWord();
        // keyword.setAddress("China");
        // List<KeyWord> msg = new ArrayList<KeyWord>();
        // msg.add(keyword);
        // /** 构造数据发送对象 */
        // kafka.javaapi.producer.Producer<String, KeyWord> producer = new kafka.javaapi.producer.Producer<String,
        // KeyWord>(
        // config);
        // producer.send(new KeyedMessage<String, String>("world", msg.toString()));
        Integer i = 2;
        System.out.println(i == 2);
        String str = "Linux_Remote";
        System.out.println(str.contains("Linux"));
    }
}
