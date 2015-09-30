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
package cjy.com.max;

/**
 * <p>测试java数值类型的最大值。<p>
 * 
 * 创建日期 2013-7-26<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestMaxValue {
    public static void main(String[] args) {
        // 整数
        System.out.println(Integer.MAX_VALUE);// 打印最大整数:2147483647
        System.out.println(Integer.MIN_VALUE);// 打印最小整数:-2147483648
        // 长整数：
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
        // 浮点数：
        System.out.println(Float.MAX_VALUE);
        System.out.println(Float.MIN_VALUE);
        // 双精度：
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        // 硬盘每天累加最大值
        System.out.println(3954784026624.00000 * 24 * 60);
    }
}
