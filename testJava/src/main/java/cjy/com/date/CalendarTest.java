/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月21日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class CalendarTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long l = 60 * 60 * 1000;
        long timestamp = System.currentTimeMillis();
        System.out.print(timestamp);
        String timeHour = timeFormat.format(new Date(timestamp));
        System.out.println("timeHour=" + timeHour);
        Date timeDate = timeFormat.parse(timeHour);
        long timeInMillis = timeDate.getTime();
        // 时间格式化
        String measTime = timeFormat.format(new Date(timeInMillis));
        System.out.println("measTime=" + measTime);
        String start = timeFormat.format(new Date(1413928800000l));
        System.out.println("start=" + start);
        // // 获取传入时间的小时
        Calendar cal = Calendar.getInstance();
    }
}
