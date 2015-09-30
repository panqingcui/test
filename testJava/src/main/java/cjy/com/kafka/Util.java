/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月24日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Util {
    public static byte[] serializeObject(Object object) {
        ByteArrayOutputStream saos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(saos);
            oos.writeObject(object);
            oos.flush();
        } catch (IOException e) {} finally {
            try {
                oos.close();
            } catch (IOException e) {}
            try {
                saos.close();
            } catch (IOException e) {}
        }
        return saos.toByteArray();
    }
}
