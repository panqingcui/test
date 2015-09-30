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

/**
 * <p>测试对象的大小。<p>
 * 
 * 创建日期 2012-8-21<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestObjectSize {
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
            object = new Test();
            // object = new Integer (i);
            // object = new Long (i);
            // object = new String ();
            // object = new byte [128][1]
            if (i >= 0)
                objects[i] = object;
            else {
                object = null; // Discard the warm up object
                runGC();
                heap1 = usedMemory(); // Take a before heap snapshot
            }
        }
        runGC();
        long heap2 = usedMemory(); // Take an after heap snapshot:
        final int size = Math.round(((float) (heap2 - heap1)) / count);
        System.out.println("'before' heap: " + heap1 + ", 'after' heap: " + heap2);
        System.out.println("heap delta: " + (heap2 - heap1) + ", {" + objects[0].getClass() + "} size = " + size
                + " bytes");
        for (int i = 0; i < count; ++i)
            objects[i] = null;
        objects = null;
    }

    private static void runGC() throws Exception {
        // It helps to call Runtime.gc()
        // using several method calls:
        for (int r = 0; r < 4; ++r)
            _runGC();
    }

    @SuppressWarnings("static-access")
    private static void _runGC() throws Exception {
        long usedMem1 = usedMemory(), usedMem2 = Long.MAX_VALUE;
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
}
