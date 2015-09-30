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

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-8-9<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestDouble {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // Java浮点型数值在大于9999999.0就自动转化为科学记数法来表示
        System.out.println(9999999.0);
        System.out.println(10000000.0);
        // java保留两位小数，方式一
        double doubleVlaue = 111231.5585;
        BigDecimal bigDecimal = new BigDecimal(doubleVlaue);
        double doubleVlaueOne = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("doubleVlaue=" + doubleVlaue);
        System.out.println("doubleVlaueOne=" + doubleVlaueOne);
        // java保留两位小数，方式二
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double doubleVlaueTwo = Double.valueOf(decimalFormat.format(doubleVlaue));
        System.out.println("doubleVlaueTwo=" + doubleVlaueTwo);
        // java保留两位小数，方式三
        String result = String.format("%.2f", doubleVlaue);
        double doubleVlaueThird = Double.valueOf(result);
        System.out.println("doubleVlaueThird=" + doubleVlaueThird);
        // 判断数值大小
        // doubleVlaue = 9999999999999.99;
        doubleVlaue = 999.10;
        double tValue = doubleVlaue / 1000 / 1000 / 1000 / 1000;
        double gValue = doubleVlaue / 1000 / 1000 / 1000;
        double mValue = doubleVlaue / 1000 / 1000;
        double kValue = doubleVlaue / 1000;
        if (tValue > 1) {
            String valueStr = decimalFormat.format(tValue);
            System.out.println("valueStr=" + valueStr + "TB");
        } else if (gValue > 1) {
            String valueStr = decimalFormat.format(gValue);
            System.out.println("valueStr=" + valueStr + "GB");
        } else if (mValue > 1) {
            String valueStr = decimalFormat.format(mValue);
            System.out.println("valueStr=" + valueStr + "MB");
        } else if (kValue > 1) {
            String valueStr = decimalFormat.format(kValue);
            System.out.println("valueStr=" + valueStr + "KB");
        } else {
            String valueStr = decimalFormat.format(doubleVlaue);
            System.out.println("valueStr=" + valueStr + "B");
        }
    }
}
