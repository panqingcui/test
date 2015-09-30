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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-8-21<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestSizeOf {
    private static final Runtime s_runtime = Runtime.getRuntime();

    public static void main(String[] args) throws Exception {
        // Warm up all classes/methods we will use
        runGC();
        usedMemory();
        // Array to keep strong references to allocated objects
        final int count = 100000;
        Object[] objects = new Object[count];
        long heap1 = 0;
        // Allocate count+1 objects, discard the first one
        for (int i = -1; i < count; ++i) {
            Object object = null;
            // Instantiate your data here and assign it to object
            object = new Object();
            // object = new Integer (i);
            // object = new Long (i);
            // object = new String ();
            // object = new byte [128][1];
            if (i >= 0) {
                objects[i] = object;
            } else {
                object = null; // Discard the warm up object
                runGC();
                heap1 = usedMemory(); // Take a before heap snapshot
            }
        }
        runGC();
        long heap2 = usedMemory(); // Take an after heap snapshot:
        final int size = Math.round(((float) (heap2 - heap1)) / count);
        System.out.println("after heap(" + heap2 + ")-before heap(" + heap1 + ")=" + (heap2 - heap1) + "\nclassName="
                + objects[0].getClass().getName() + " size=" + size + "bytes");
        for (int i = 0; i < count; ++i) {
            objects[i] = null;
        }
        objects = null;
        Map<String, String> map = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 6000; i++) {
            map.put(i + "", "http://www.baidu.comhttp://www.baidu.comhttp://www.baidu.comhttp://www.baidu.com"
                    + "http://www.baidu.comhttp://www.baidu.comhttp://www.baidu.comhttp://www.baidu.com"
                    + "http://www.baidu.comhttp://www.baidu.comhttp://www.baidu.comhttp://www.baidu.com");
        }
        System.out.println("耗时" + (System.currentTimeMillis() - start) + "毫秒，" + "占用内存大小" + sizeOfMap(map) / (1024)
                + "KB");
    }

    private static void runGC() throws Exception {
        // It helps to call Runtime.gc()
        // using several method calls:
        for (int r = 0; r < 4; ++r) {
            _runGC();
        }
    }

    @SuppressWarnings("static-access")
    private static void _runGC() throws Exception {
        long usedMem1 = usedMemory();
        long usedMem2 = Long.MAX_VALUE;
        for (int i = 0; (usedMem1 < usedMem2) && (i < 500); ++i) {
            s_runtime.runFinalization();
            s_runtime.gc();
            Thread.currentThread().yield();
            usedMem2 = usedMem1;
            usedMem1 = usedMemory();
        }
    }

    private static long usedMemory() {
        return s_runtime.totalMemory() - s_runtime.freeMemory();
    }

    public static int sizeOfObject() {
        return 4;
    }

    public static long sizeOfString(String string) {
        if (string == null)
            return 0;
        else
            return 4 + string.length() * 2;
    }

    public static int sizeOfInt() {
        return 4;
    }

    public static int sizeOfChar() {
        return 2;
    }

    public static int sizeOfBoolean() {
        return 1;
    }

    public static int sizeOfLong() {
        return 8;
    }

    public static int sizeOfDouble() {
        return 8;
    }

    public static int sizeOfDate() {
        return 12;
    }

    public static long sizeOfMap(Map<String, String> map) {
        if (map == null)
            return 0;
        long size = 36;
        Object values[] = map.values().toArray();
        for (int i = 0; i < values.length; i++)
            size += sizeOfString((String) values[i]);
        Object keys[] = map.keySet().toArray();
        for (int j = 0; j < keys.length; j++) {
            size += sizeOfString((String) keys[j]);
        }
        return size;
    }
    // public static int sizeOfList(List list) {
    // if (list == null)
    // return 0;
    // int size = 36;
    // Object values[] = list.toArray();
    // for (int i = 0; i < values.length; i++) {
    // Object obj = values[i];
    // if (obj instanceof String) {
    // size += sizeOfString((String) obj);
    // continue;
    // }
    // if (obj instanceof Long)
    // size += sizeOfLong() + sizeOfObject();
    // else
    // size += ((Cacheable) obj).getCachedSize();
    // }
    // return size;
    // }
}
