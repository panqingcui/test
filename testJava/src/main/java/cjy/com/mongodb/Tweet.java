/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.mongodb;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.DBObject;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月28日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Tweet implements DBObject {
    private Map<String, Object> data;
    private boolean partial;

    public Tweet() {
        data = new HashMap<String, Object>();
        partial = false;
    }

    @Override
    public Object put(String key, Object value) {
        return data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void putAll(BSONObject o) {
        data.putAll(o.toMap());
    }

    @SuppressWarnings({"rawtypes", "unchecked" })
    @Override
    public void putAll(Map m) {
        data.putAll(m);
    }

    @Override
    public Object get(String key) {
        return data.get(key);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map toMap() {
        return data;
    }

    @Override
    public Object removeField(String key) {
        return data.remove(key);
    }

    @Override
    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsField(String key) {
        return data.containsKey(key);
    }

    @Override
    public Set<String> keySet() {
        return data.keySet();
    }

    @Override
    public void markAsPartialObject() {
        partial = true;
    }

    @Override
    public boolean isPartialObject() {
        return partial;
    }
}
