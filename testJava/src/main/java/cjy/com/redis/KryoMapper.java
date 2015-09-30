/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月20日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class KryoMapper {
    private static ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(false);
            kryo.setReferences(true);
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            return kryo;
        }
    };

    public static Kryo get() {
        return kryos.get();
    }

    // 序列化object-->byte[]
    public static byte[] toKryo(Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        Kryo kryo = get();
        kryo.writeObjectOrNull(output, object, object.getClass());
        byte[] bs = output.toBytes();
        bos.close();
        output.close();
        return bs;
    }

    // 反序列化byte[]-->t
    public static <T> T fromKryo(byte[] bs, Class<T> clazz) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bs);
        Input input = new Input(bis);
        Kryo kryo = get();
        T t = (T) kryo.readObjectOrNull(input, clazz);
        bis.close();
        input.close();
        return t;
    }

    public static int byte2int(byte[] byt) {
        return (int) (byt[0] & 0xff | (byt[1] & 0xff) << 8 | (byt[2] & 0xff) << 16 | (byt[3] & 0xff) << 24);
    }

    public static byte[] int2byte(int number) {
        byte[] byt = new byte[4];
        byt[0] = (byte) (number & 0xff);
        byt[1] = (byte) (number >> 8 & 0xff);
        byt[2] = (byte) (number >> 16 & 0xff);
        byt[3] = (byte) (number >> 24 & 0xff);
        return byt;
    }
}
