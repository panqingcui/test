/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月29日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class JSONUtil {
    /**
     * 转成json字符串
     * @param object
     * @return
     */
    public static DBObject toDBObject(Object object) {
        Gson gson = new Gson();
        return (DBObject) JSON.parse(gson.toJson(object));
    }

    /**
     * 转成对象
     * @param json
     * @param value
     * @return
     */
    public static <T> T toObject(DBObject object, Class<T> value) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), value);
    }
}
