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

/**
 * <p>测试天和小时判断。<p>
 * 
 * 创建日期 2013-3-22<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestDayAndHour {
    // 当期是那一天
    public static int day = 0;
    // 当期是那一小时
    public static int hour = 0;
    // 一天最多发送的短信计数
    public static int dayCount = 0;
    // 一小时最多发送的短信计数
    public static int hourCount = 0;
    // 一天最多发送的短信条数
    public static int dayMaxCount = 30;
    // 一小时最多发送的短信条数
    public static int hourMaxCount = 5;

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // -----------------------------------------------------
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println("day:" + day);
        System.out.println("hour:" + hour);
        // -----------------------------------------------------
        Calendar calCur = Calendar.getInstance();
        calCur.set(2013, 2, 18, 8, 8, 8);
        long currTime = calCur.getTimeInMillis();
        Date startDate = new Date(currTime);
        String startTime = dateFormat.format(startDate);
        System.out.println("startTime:" + startTime);
        day = calCur.get(Calendar.DAY_OF_MONTH);
        hour = calCur.get(Calendar.HOUR_OF_DAY);
        System.out.println("day:" + day);
        System.out.println("hour:" + hour);
        // -----------------------------------------------------
        // long currTime = System.currentTimeMillis();
        // -----------------------------------------------------
        Calendar calEnd = Calendar.getInstance();
        calEnd.set(2013, 2, 19, 9, 8, 8);
        long endTimeMill = calEnd.getTimeInMillis();
        Date endDate = new Date(endTimeMill);
        String endTime = dateFormat.format(endDate);
        System.out.println("endTime:" + endTime);
        int endDay = calEnd.get(Calendar.DAY_OF_MONTH);
        int endHour = calEnd.get(Calendar.HOUR_OF_DAY);
        System.out.println("endDay:" + endDay);
        System.out.println("endHour:" + endHour);
        // 校验当天日期和小时
        if (day != endDay) {
            dayCount += 1;
        }
        if (hour != endHour) {
            hourCount += 1;
        }
        System.out.println("dayCount:" + dayCount);
        System.out.println("hourCount:" + hourCount);
    }
}
