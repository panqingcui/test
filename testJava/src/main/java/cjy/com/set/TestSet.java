/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.com.set;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2013-4-15<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestSet {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("6688");
        set.add("8866");
        set.remove("8888");
        set.remove("6688");
        for (String value : set) {
            System.out.println("value: " + value);
        }
        // 测试循环中删除set是否会报错
        set.clear();
        set.add("8886");
        set.add("8887");
        set.add("8888");
        for (String value : set) {
            if (value.equals("8887")) {
                set.remove(value);
            }
        }
        for (String value : set) {
            System.out.println("循环中删除之后value: " + value);
        }
    }
}
