/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月24日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class HashSimplePartitioner implements Partitioner {
    public HashSimplePartitioner(VerifiableProperties props) {}

    @Override
    public int partition(Object key, int numPartitions) {
        System.out.println("hashpartition ---> " + key);
        return key.hashCode() % numPartitions;
    }
}
