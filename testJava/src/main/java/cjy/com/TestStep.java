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
package cjy.com;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2013-6-13<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestStep {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // int i = 1;
        // for (int n = 1; n <= 11; n = n + 2) {
        // System.out.println("计算之前 n = " + n);
        // System.out.println("计算之前 i = " + i);
        // i = 2 * i + 1;
        // if (i > 20) {
        // i = i - 20;
        // }
        // System.out.println("计算之后 i = " + i + "\n");
        // }
        int a = 9;
        int b = 5;
        int x = a / b;
        if (a > b) {
            x = a % b;
        }
        System.out.println("计算之后 x = " + x + "\n");
    }
}
