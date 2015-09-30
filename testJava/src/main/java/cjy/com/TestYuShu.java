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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>测试余数。<p>
 * 
 * 创建日期 2012-10-23<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestYuShu {
    private static Calendar _baseCal = Calendar.getInstance();
    public static final int NUMBER_OF_TABLES = 14, NUMBER_OF_TABLES_PER_DAY = 2;
    public static final String MEAS_TABLE = "dl_metric_data";
    private static List<MeasRange> _ranges = new ArrayList<MeasRange>();
    private static List<MeasRange> _umRanges;
    static {
        _baseCal.set(2006, 0, 1, 0, 0);
    }

    private static int getDayOfPeriod(Calendar cal, long timems) {
        int rtn = 0;
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(new java.util.Date(timems));
        while (cal.get(Calendar.YEAR) >= _baseCal.get(Calendar.YEAR)) {
            if (cal.get(Calendar.YEAR) == currCal.get(Calendar.YEAR)) {
                rtn += currCal.get(Calendar.DAY_OF_YEAR);
            } else {
                rtn += cal.get(Calendar.DAY_OF_YEAR);
            }
            cal.add(Calendar.YEAR, -1);
            cal.set(Calendar.MONTH, 11);
            cal.set(Calendar.DAY_OF_MONTH, 31);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 0);
        }
        return rtn;
    }

    /**
     * 计算度量范围的起始时间。
     * @param cal
     * @param timems
     * @return
     */
    public static long getMeasTabStartTime(Calendar cal, long timems) {
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        // 每天两张表，incr=12。
        int incr = 24 / NUMBER_OF_TABLES_PER_DAY;
        for (int i = incr; i <= 24; i += incr) {
            if (cal.get(Calendar.HOUR_OF_DAY) < i) {
                // 设置开始时间小时数为0。
                cal.set(Calendar.HOUR_OF_DAY, i - incr);
                break;
            }
        }
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 设置开始时间小时数为00:00:00或12:00:00。
        return cal.getTimeInMillis();
    }

    /**
     * 计算度量范围的结束时间。
     * @param cal
     * @param timems
     * @return
     */
    public static long getMeasTabEndTime(Calendar cal, long timems) {
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        // 每天两张表，incr=12。
        int incr = 24 / NUMBER_OF_TABLES_PER_DAY;
        for (int i = incr; i <= 24; i += incr) {
            if (cal.get(Calendar.HOUR_OF_DAY) < i) {
                // 设置结束时间小时数为11或23。
                cal.set(Calendar.HOUR_OF_DAY, i - 1);
                break;
            }
        }
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        // 设置结束时间小时数为11:59:59或23:59:59。
        return cal.getTimeInMillis();
    }

    public static String getMeasTabname(long timems) {
        Calendar cal = Calendar.getInstance();
        return getMeasTabname(cal, timems);
    }

    /**
     * 根据当前时间返回表名。
     * @param cal
     * @param timems
     * @return
     */
    public static String getMeasTabname(Calendar cal, long timems) {
        // 基于_baseCal.set(2006, 0, 1, 0, 0)将当前时间转换为天。
        int dayofperiod = getDayOfPeriod(cal, timems);
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        int numdaytables = NUMBER_OF_TABLES / NUMBER_OF_TABLES_PER_DAY;
        // 对度量值表的个数取余得到表名的天数。
        int daytable = dayofperiod % numdaytables;
        // 一天24小时对每天的度量值表的个数取余。
        int dayincr = 24 / NUMBER_OF_TABLES_PER_DAY;
        int dayslice = 0;
        // 根据当前小时数判断在天的哪个度量值表中。
        for (int i = dayincr; i < 24; i += dayincr) {
            if (hourofday < i) {
                break;
            }
            dayslice++;
        }
        return MEAS_TABLE + "_" + daytable + "d_" + dayslice + "s";
    }

    public static long getPrevMeasTabTime(Calendar cal, long timems) {
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        long rtn = -1;
        // need to do this because of DST
        // add() and roll() don't work right
        String currTable = getMeasTabname(timems);
        String newTable;
        int incr = 24 / NUMBER_OF_TABLES_PER_DAY / 3;
        incr = ((incr < 1) ? 1 : incr) * -1;
        do {
            cal.add(Calendar.HOUR_OF_DAY, incr);
            rtn = cal.getTimeInMillis();
            newTable = getMeasTabname(rtn);
        } while (currTable.equals(newTable));
        return rtn;
    }

    private static void setRanges() {
        System.out.println("setRanges start --------------------------------------------------");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long currTime = System.currentTimeMillis();
        String currTable = getMeasTabname(cal, currTime);
        currTime = getMeasTabStartTime(cal, currTime);
        String table = currTable;
        long max = getMeasTabEndTime(cal, currTime);
        System.out.println("currTime:" + dateFormat.format(new Date(currTime)));
        System.out.println("max:" + dateFormat.format(new Date(max)));
        System.out.println("table:" + table);
        MeasRange range = new MeasRange(currTable, currTime, max);
        synchronized (_ranges) {
            do {
                _ranges.add(range);
                currTime = getPrevMeasTabTime(cal, currTime);
                max = getMeasTabEndTime(cal, currTime);
                currTime = getMeasTabStartTime(cal, currTime);
                table = getMeasTabname(cal, currTime);
                System.out.println("currTime:" + dateFormat.format(new Date(currTime)));
                System.out.println("max:" + dateFormat.format(new Date(max)));
                System.out.println("table:" + table);
                range = new MeasRange(table, currTime, max);
            } while (!currTable.equals(table));
            _umRanges = Collections.unmodifiableList(_ranges);
        }
        System.out.println("setRanges end --------------------------------------------------");
    }

    public static void getYuShu() {
        Calendar cal = Calendar.getInstance();
        // -----------------------------------------------------
        Calendar calCur = Calendar.getInstance();
        calCur.set(2012, 9, 23, 10, 0);
        long currTime = calCur.getTimeInMillis();
        // -----------------------------------------------------
        // long currTime = System.currentTimeMillis();
        // -----------------------------------------------------
        int dayofperiod = getDayOfPeriod(cal, currTime);
        // -----------------------------------------------------
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        int numdaytables = NUMBER_OF_TABLES / NUMBER_OF_TABLES_PER_DAY;
        // 对度量值表的个数取余得到表名的天数。
        int daytable = dayofperiod % numdaytables;
        // 一天24小时对每天的度量值表的个数取余。
        int dayincr = 24 / NUMBER_OF_TABLES_PER_DAY;
        int dayslice = 0;
        // 根据当前小时数判断在天的哪个度量值表中。
        for (int i = dayincr; i < 24; i += dayincr) {
            if (hourofday < i) {
                break;
            }
            dayslice++;
        }
        System.out.println("dayofperiod:" + dayofperiod);
        System.out.println("hourofday:" + hourofday);
        System.out.println("numdaytables:" + numdaytables);
        System.out.println("daytable:" + daytable);
        System.out.println("dayincr:" + dayincr);
        System.out.println("dayslice:" + dayslice);
        System.out.println(MEAS_TABLE + "_" + daytable + "d_" + dayslice + "s");
        // -----------------------------------------------------
        long minTime = getMeasTabStartTime(cal, currTime);
        long maxTime = getMeasTabEndTime(cal, currTime);
        // -----------------------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date(minTime);
        String startTime = dateFormat.format(startDate);
        Date endDate = new Date(maxTime);
        String endTime = dateFormat.format(endDate);
        System.out.println("startTime:" + startTime);
        System.out.println("endTime:" + endTime);
        // -----------------------------------------------------
        long preTime = getPrevMeasTabTime(cal, currTime);
        Date preDate = new Date(preTime);
        String preTimeStr = dateFormat.format(preDate);
        System.out.println("preTimeStr:" + preTimeStr);
    }

    /**
     * Get the array of tables that fall in the time range
     */
    public static String[] getMetricTables(long begin, long end) {
        List<MeasRange> ranges = _umRanges;
        String[] tables = new String[ranges.size()];
        int i = 0;
        for (MeasRange range : ranges) {
            long rBegin = range.getMinTimestamp();
            long rEnd = range.getMaxTimestamp();
            if (begin > rEnd || end < rBegin) {
                continue;
            }
            tables[i++] = range.getTable();
        }
        // Now we want to trim the empties
        String[] retTables = new String[i];
        for (i = 0; i < retTables.length; i++) {
            retTables[i] = tables[i];
        }
        return retTables;
    }

    public static void main(String[] args) {
        getYuShu();
        setRanges();
        Calendar calBegin = Calendar.getInstance();
        calBegin.set(2012, 9, 17, 14, 0);
        Calendar calEnd = Calendar.getInstance();
        calEnd.set(2012, 9, 23, 14, 0);
        long begin = calBegin.getTimeInMillis();
        long end = calEnd.getTimeInMillis();
        String[] tables = getMetricTables(begin, end);
        for (String table : tables) {
            System.out.println("table:" + table);
        }
    }
}
