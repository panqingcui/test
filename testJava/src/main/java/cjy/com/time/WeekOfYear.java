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
package cjy.com.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <p>跨年周数的计算 。<p>
 * 
 * 创建日期 2012-10-24<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class WeekOfYear {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd , ww, E");
        // sdf.format("2009-12-31");
        Calendar cl = Calendar.getInstance();
        // 每周以周日开始
        cl.setFirstDayOfWeek(GregorianCalendar.MONDAY);
        // 每年的第一周必须大于或等于3天，否则就算上一年的最后一周
        cl.setMinimalDaysInFirstWeek(3);
        // 使用SimpleDateFormat获取的周数是错误的，get(Calendar.WEEK_OF_YEAR)是对的
        cl.set(2009, 11, 26);
        System.out.println(sdf.format(cl.getTime()) + "/t" + cl.get(Calendar.WEEK_OF_YEAR) + "/t" + getWeekOfYear(cl));
        cl.set(2009, 11, 27);
        System.out.println(sdf.format(cl.getTime()) + "/t" + cl.get(Calendar.WEEK_OF_YEAR) + "/t" + getWeekOfYear(cl));
        cl.set(2010, 0, 1);
        System.out.println(sdf.format(cl.getTime()) + "/t" + cl.get(Calendar.WEEK_OF_YEAR) + "/t" + getWeekOfYear(cl));
        cl.set(2011, 0, 2);
        System.out.println(sdf.format(cl.getTime()) + "/t" + cl.get(Calendar.WEEK_OF_YEAR) + "/t" + getWeekOfYear(cl));
    }

    // 但前日期所在的周属于上一年或是下一年。13017614465
    public static int getWeekOfYear(Calendar cl) {
        if (cl.get(Calendar.MONTH) == Calendar.JANUARY && cl.get(Calendar.WEEK_OF_YEAR) > 50) {
            return cl.get(Calendar.YEAR) - 1;
        } else {
            return cl.get(Calendar.YEAR);
        }
    }
}
