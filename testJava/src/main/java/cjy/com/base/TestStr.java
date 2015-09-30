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
package cjy.com.base;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-8-6<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestStr {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // String warning = "19,20,36";
        // System.out.println("warning: " + warning);
        // // 删除字符串中包含的字符子串
        // if (warning.contains("20")) {
        // warning = warning.replace("20" + ",", "");
        // System.out.println("warning: " + warning);
        // } else {
        // System.out.println("warning not find");
        // }
        // // 字符子串如果不存在，则添加到字符串
        // if (!warning.contains("88")) {
        // warning += ",88";
        // System.out.println("warning: " + warning);
        // }
        String str = "40.0%";
        System.out.println(str.replace(".0%", "%"));
    }
}
