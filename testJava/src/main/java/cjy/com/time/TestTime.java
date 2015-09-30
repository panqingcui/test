/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.com.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>测试时间。<p>
 * 
 * 创建日期 2012-10-30<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestTime {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        // SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        // long coolTime = 1369886460000L;
        // long coolTime = 1369886640000L;
        // long coolTime = 1384358400000L;
        long coolTime = 1384876920000L;
        Date startDate = new Date(coolTime);
        String startTime = dateFormat.format(startDate);
        System.out.println("startTime  : " + startTime);
        // // Calendar时间加一小时
        // Calendar cal = Calendar.getInstance();
        // cal.setTimeInMillis(coolTime);
        // cal.add(Calendar.HOUR, 1);
        // String endTime = dateFormat.format(new Date(cal.getTimeInMillis()));
        // System.out.println("endTime    : " + endTime);
        // // 用毫秒加一小时时间
        // long hourMillis = 60 * 60 * 1000;
        // long coolEndTime = coolTime + hourMillis;
        // String endTwoTime = dateFormat.format(new Date(coolEndTime));
        // System.out.println("endTwoTime : " + endTwoTime);
        // // 时间规整到小时
        // Date now = new Date();
        // long nowMillis = now.getTime();
        // System.out.println("nowMillis : " + nowMillis);
        // System.out.println("now date : " + dateFormat.format(now));
        // try {
        // now = dateFormat.parse(dateFormat.format(now));
        // } catch (ParseException e) {-
        // System.err.println("数据转换时出错");
        // }
        // long nowFormatMillis = now.getTime();
        // System.out.println("nowFormatMillis : " + nowFormatMillis);
        // System.out.println("now date : " + dateFormat.format(now));
        // nowFormatMillis += hourMillis;
        // System.out.println("now last date : " + dateFormat.format(new
        // Date(nowFormatMillis)));
    }
}
