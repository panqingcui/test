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
package cjy.com.object;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-8-21<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class CacheSizes {
    /**
     * Returns the size in bytes of a basic Object. This method should only be
     * used for actual Object objects and not classes that extend Object.
     * 
     * @return the size of an Object.
     */
    public static int sizeOfObject() {
        return 4;
    }

    /**
     * Returns the size in bytes of a String.
     * 
     * @param string the String to determine the size of.
     * @return the size of a String.
     */
    public static int sizeOfString(String string) {
        if (string == null) {
            return 0;
        }
        return 4 + string.length() * 2;
    }

    /**
     * Returns the size in bytes of a primitive int.
     * 
     * @return the size of a primitive int.
     */
    public static int sizeOfInt() {
        return 4;
    }

    /**
     * Returns the size in bytes of a primitive char.
     * 
     * @return the size of a primitive char.
     */
    public static int sizeOfChar() {
        return 2;
    }

    /**
     * Returns the size in bytes of a primitive boolean.
     * 
     * @return the size of a primitive boolean.
     */
    public static int sizeOfBoolean() {
        return 1;
    }

    /**
     * Returns the size in bytes of a primitive long.
     * 
     * @return the size of a primitive long.
     */
    public static int sizeOfLong() {
        return 8;
    }

    /**
     * Returns the size in bytes of a primitive double.
     * 
     * @return the size of a primitive double.
     */
    public static int sizeOfDouble() {
        return 8;
    }

    /**
     * Returns the size in bytes of a Date.
     * 
     * @return the size of a Date.
     */
    public static int sizeOfDate() {
        return 12;
    }

    /**
     * Returns the size in bytes of a Properties object. All properties and
     * property names must be Strings.
     * 
     * @param properties the Properties object to determine the size of.
     * @return the size of a Properties object.
     */
    @SuppressWarnings("rawtypes")
    public static int sizeOfProperties(Properties properties) {
        if (properties == null) {
            return 0;
        }
        // Base properties object
        int size = 36;
        // Add in size of each property
        Enumeration enumeration = properties.elements();
        while (enumeration.hasMoreElements()) {
            String prop = (String) enumeration.nextElement();
            size += sizeOfString(prop);
        }
        // Add in property names
        enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String prop = (String) enumeration.nextElement();
            size += sizeOfString(prop);
        }
        return size;
    }

    /**
     * Returns the size in bytes of a Map object. All keys and values <b>must be
     * Strings</b>.
     * 
     * @param map the Map object to determine the size of.
     * @return the size of the Map object.
     */
    public static int sizeOfMap(Map<?, ?> map) {
        if (map == null) {
            return 0;
        }
        // Base map object -- should be something around this size.
        int size = 36;
        // Add in size of each value
        Iterator<?> iter = map.values().iterator();
        while (iter.hasNext()) {
            String value = (String) iter.next();
            size += sizeOfString(value);
        }
        // Add in each key
        iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            size += sizeOfString(key);
        }
        return size;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}
