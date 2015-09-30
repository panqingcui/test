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
 * <p>测试获取小时功能。<p>
 * 
 * 创建日期 2013-8-2<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestHour {
    public static void main(String[] args) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar calendar = Calendar.getInstance();
        long curTime = calendar.getTimeInMillis();
        Date dateNow = new Date(curTime);
        int hourNow = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("current date = " + timeFormat.format(dateNow));
        System.out.println("current hour = " + hourNow);
        // 测试小时时间
        Calendar calHour = Calendar.getInstance();
        calHour.set(2013, 8, 1, 0, 1, 18);
        long hourTime = calHour.getTimeInMillis();
        Date dateHour = new Date(hourTime);
        int hourBefor = calHour.get(Calendar.HOUR_OF_DAY);
        System.out.println("hour date = " + timeFormat.format(dateHour));
        System.out.println("befor hour = " + hourBefor);
        // 当前小时比较
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(hourTime);
        int hourCal = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println("cal hour = " + hourCal);
    }
}
