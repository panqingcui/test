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
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>Java取一年有多少周、某周的第一天和最后一天 。<p>
 * 
 * 创建日期 2012-10-24<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class WeekUtils {
    // 获取当前时间所在年的周数
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        int weeks = c.get(Calendar.WEEK_OF_YEAR);
        if (c.get(Calendar.MONTH) == Calendar.JANUARY && weeks > 50) {
            weeks = 0;
        }
        return weeks;
    }

    // 获取当前时间所在年的最大周数
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekOfYear(c.getTime());
    }

    // 获取某年的第几周的开始日期
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }

    // 获取某年的第几周的结束日期
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    // 获取当前时间所在周的开始日期
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    // 获取当前时间所在周的结束日期
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int year = 2006;
        int week = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 1, 0, 0, 0);
        long curTime = calendar.getTimeInMillis();
        Date today = new Date(curTime);
        // Calendar c = new GregorianCalendar();
        // c.setTime(today);
        System.out.println("current date = " + sdf.format(today));
        System.out.println("getWeekOfYear = " + getWeekOfYear(today));
        System.out.println("getMaxWeekNumOfYear = " + getMaxWeekNumOfYear(year));
        System.out.println("getFirstDayOfWeek = " + sdf.format(getFirstDayOfWeek(year, week)));
        System.out.println("getLastDayOfWeek = " + sdf.format(getLastDayOfWeek(year, week)));
        System.out.println("getFirstDayOfWeek = " + sdf.format(getFirstDayOfWeek(today)));
        System.out.println("getLastDayOfWeek = " + sdf.format(getLastDayOfWeek(today)));
    }
}
