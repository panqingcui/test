/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.reflect;

import java.lang.reflect.Array;

/**
 * <p>通过反射取得并修改数组的信息：。<p>
 * 
 * 创建日期 2014年1月16日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Demo_011 {
    public static void main(String[] args) {
        int temp[] = {1, 2, 3, 4 };
        Class<?> demo = temp.getClass().getComponentType();
        System.out.println("数组类型： " + demo.getName());
        System.out.println("数组长度  " + Array.getLength(temp));
        System.out.println("数组的第一个元素: " + Array.get(temp, 0));
        Array.set(temp, 0, 100);
        Array.set(temp, 1, 200);
        System.out.println("修改之后数组第一个元素为： " + Array.get(temp, 0));
        System.out.println("修改之后数组第二个元素为： " + Array.get(temp, 1));
    }
}
