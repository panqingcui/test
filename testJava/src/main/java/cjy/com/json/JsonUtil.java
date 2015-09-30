/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package cjy.com.json;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * <p>Json 工具类。<p>
 * 
 * 创建日期 2012-5-30<br>
 * @author liangpengfei<br>
 * @version 3.0.0
 * @param <T>
 * @since 3.0.0
 */
public class JsonUtil<T> {
    // JSON工具类，将列表转成JSON字符串
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转化为json string
     * @param obj
     * @return
     */
    public static String convertObjToString(Object obj) {
        String reString = null;
        try {
            reString = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reString;
    }

    /**
     * 将json string转化为指定类型对象
     * @param <T>
     * @param string
     * @param obj
     * @return
     */
    public static <T> T convertStringToObj(String string, Class<T> valueType) {
        T t = null;
        try {
            t = objectMapper.readValue(string, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
}
