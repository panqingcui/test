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

import java.util.Calendar;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2012-10-24<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class LoopDayOfMonth {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2007, 11, 29, 23, 59, 59);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("Max Day: " + maxDay);
        int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        System.out.println("Min Day: " + minDay);
        int weeks = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("weeks: " + weeks);
        int months = calendar.get(Calendar.MONTH) + 1;
        System.out.println("months: " + months);
        int days = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println("days: " + days);
        // for (int i = minDay; i <= maxDay; i++) {
        // calendar.set(year, month, i);
        // System.out.println("Day: " + calendar.getTime().toLocaleString());
        // }
    }
}
