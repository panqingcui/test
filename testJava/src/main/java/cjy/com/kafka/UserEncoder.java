/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

import kafka.message.Message;
import kafka.utils.VerifiableProperties;

import com.alibaba.fastjson.JSON;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月23日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class UserEncoder implements kafka.serializer.Encoder<KeyWord> {
    public UserEncoder(VerifiableProperties propertis) {}

    public Message toMessage(KeyWord data) {
        return new Message(data.toString().getBytes());
    }

    /*
     * @see kafka.serializer.Encoder#toBytes(java.lang.Object)
     */
    @Override
    public byte[] toBytes(KeyWord arg0) {
        return JSON.toJSONString(arg0).getBytes();
    }
}
