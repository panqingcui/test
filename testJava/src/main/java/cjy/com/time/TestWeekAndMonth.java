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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cjy.com.MeasRange;

/**
 * <p>测试获取星期数和月数。<p>
 * 
 * 创建日期 2012-10-24<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestWeekAndMonth {
    private static Calendar _baseCal = Calendar.getInstance();
    // 存储1h聚集度量数据的聚集表总数，每周数据存一张表，默认存最近1季度数据
    public static final int NUMBER_OF_MEAS_1H_TABLES = 12;
    // 存储1h聚集度量数据的聚集表前缀
    public static final String MEAS_1H_TABLE = "dl_measurement_data_1h";
    // 存储6h聚集度量数据的聚集表总数，每双周数据存一张表，默认存最近半年数据
    public static final int NUMBER_OF_MEAS_6H_TABLES = 12;
    // 存储6h聚集度量数据的聚集表前缀
    public static final String MEAS_6H_TABLE = "dl_measurement_data_6h";
    // 存储1d聚集度量数据的聚集表总数，每月数据存一张表，默认存最近1年数据
    public static final int NUMBER_OF_MEAS_1D_TABLES = 12;
    // 存储1d聚集度量数据的聚集表前缀
    public static final String MEAS_1D_TABLE = "dl_measurement_data_1d";
    // 度量数据1h聚集表列表
    private static List<MeasRange> _meas1hRanges = new ArrayList<MeasRange>();
    // 只读度量数据1h聚集表列表
    private static List<MeasRange> _meas1hUmRanges;
    // 度量数据6h聚集表列表
    private static List<MeasRange> _meas6hRanges = new ArrayList<MeasRange>();
    // 只读度量数据6h聚集表列表
    private static List<MeasRange> _meas6hUmRanges;
    // 度量数据1d聚集表列表
    private static List<MeasRange> _meas1dRanges = new ArrayList<MeasRange>();
    // 只读度量数据1d聚集表列表
    private static List<MeasRange> _meas1dUmRanges;
    static {
        _baseCal.set(2006, 0, 1, 0, 0);
    }

    // 获取当前时间所在年的最大周数
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekOfYear(c.getTimeInMillis());
    }

    // 获取某年的第几周的开始日期
    public static long getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTimeInMillis());
    }

    // 获取某年的第几周的结束日期
    public static long getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTimeInMillis());
    }

    // 获取当前时间所在周的开始日期
    public static long getFirstDayOfWeek(long timems) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTimeInMillis(timems);
        // 星期一
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    // 获取当前时间所在周的结束日期
    public static long getLastDayOfWeek(long timems) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTimeInMillis(timems);
        // 星期日
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTimeInMillis();
    }

    // 获取当前时间所在双周的开始日期
    public static long getFirstDayOfTwoWeek(long timems) {
        int weeks = getWeekOfYear(timems);
        // 双周，向前推一周
        if (weeks % 2 == 0) {
            timems = timems - 7 * 24 * 60 * 60 * 1000;
        }
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTimeInMillis(timems);
        // 星期一
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    // 获取当前时间所在双周的结束日期
    public static long getLastDayOfTwoWeek(long timems) {
        int weeks = getWeekOfYear(timems);
        // 单周，向后推一周
        if (weeks % 2 != 0) {
            timems = timems + 7 * 24 * 60 * 60 * 1000;
        }
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTimeInMillis(timems);
        // 星期日
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTimeInMillis();
    }

    // 获取当前时间所在月的开始日期
    public static long getFirstDayOfMonth(long timems) {
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(timems);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    // 获取当前时间所在月的结束日期
    public static long getLastDayOfMonth(long timems) {
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(timems);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTimeInMillis();
    }

    // 获取当前时间所在年的周数
    public static int getWeekOfYear(long timems) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTimeInMillis(timems);
        int weeks = c.get(Calendar.WEEK_OF_YEAR);
        if (c.get(Calendar.MONTH) == Calendar.JANUARY && weeks > 50) {
            weeks = 0;
        }
        return weeks;
    }

    private static int getWeekOfPeriod(Calendar cal, long timems) {
        int rtn = 0;
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        while (cal.get(Calendar.YEAR) >= _baseCal.get(Calendar.YEAR)) {
            rtn += getWeekOfYear(cal.getTimeInMillis());
            cal.add(Calendar.YEAR, -1);
            cal.set(Calendar.MONTH, 11);
            cal.set(Calendar.DAY_OF_MONTH, 31);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 0);
        }
        return rtn;
    }

    private static int getTwoWeekOfPeriod(Calendar cal, long timems) {
        int rtn = 0;
        int weeks = getWeekOfPeriod(cal, timems);
        if (weeks % 2 == 0) {
            rtn = weeks / 2;
        } else {
            rtn = weeks / 2 + 1;
        }
        return rtn;
    }

    private static int getMonthOfPeriod(Calendar cal, long timems) {
        int rtn = 0;
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(new java.util.Date(timems));
        while (cal.get(Calendar.YEAR) >= _baseCal.get(Calendar.YEAR)) {
            if (cal.get(Calendar.YEAR) == currCal.get(Calendar.YEAR)) {
                rtn += currCal.get(Calendar.MONTH) + 1;
            } else {
                rtn += cal.get(Calendar.MONTH) + 1;
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
     * 根据当前时间1h度量聚集表名。
     * @param cal
     * @param timems
     * @return
     */
    public static String getMeas1hTabname(Calendar cal, long timems) {
        int weekofperiod = getWeekOfPeriod(cal, timems);
        System.out.println("weekofperiod:" + weekofperiod);
        int weekTable = weekofperiod % NUMBER_OF_MEAS_1H_TABLES;
        System.out.println("weekTable:" + weekTable);
        return MEAS_1H_TABLE + "_" + weekTable + "w";
    }

    /**
     * 根据当前时间6h度量聚集表名。
     * @param cal
     * @param timems
     * @return
     */
    public static String getMeas6hTabname(Calendar cal, long timems) {
        int twoWeekofperiod = getTwoWeekOfPeriod(cal, timems);
        System.out.println("twoWeekofperiod:" + twoWeekofperiod);
        int twoWeekTable = twoWeekofperiod % NUMBER_OF_MEAS_6H_TABLES;
        System.out.println("twoWeekTable:" + twoWeekTable);
        return MEAS_6H_TABLE + "_" + twoWeekTable + "tw";
    }

    /**
     * 根据当前时间1d度量聚集表名。
     * @param cal
     * @param timems
     * @return
     */
    public static String getMeas1dTabname(Calendar cal, long timems) {
        int monthofperiod = getMonthOfPeriod(cal, timems);
        System.out.println("monthofperiod:" + monthofperiod);
        int monthTable = monthofperiod % NUMBER_OF_MEAS_1D_TABLES;
        System.out.println("monthTable:" + monthTable);
        return MEAS_1D_TABLE + "_" + monthTable + "m";
    }

    /**
     * 获取前一张1h度量聚集表名
     * @param cal
     * @param timems
     * @return
     */
    public static long getPrevMeas1hTabTime(Calendar cal, long timems) {
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        long rtn = -1;
        String currMeas1hTable = getMeas1hTabname(timems);
        String newMeas1hTable;
        int incr = -3;
        do {
            cal.add(Calendar.DAY_OF_WEEK, incr);
            rtn = cal.getTimeInMillis();
            newMeas1hTable = getMeas1hTabname(rtn);
        } while (currMeas1hTable.equals(newMeas1hTable));
        return rtn;
    }

    /**
     * 获取前一张6h度量聚集表名
     * @param cal
     * @param timems
     * @return
     */
    public static long getPrevMeas6hTabTime(Calendar cal, long timems) {
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        long rtn = -1;
        String currMeas6hTable = getMeas6hTabname(timems);
        String newMeas6hTable;
        int incr = -6;
        do {
            cal.add(Calendar.DAY_OF_WEEK, incr);
            rtn = cal.getTimeInMillis();
            newMeas6hTable = getMeas6hTabname(rtn);
        } while (currMeas6hTable.equals(newMeas6hTable));
        return rtn;
    }

    /**
     * 获取前一张1d度量聚集表名
     * @param cal
     * @param timems
     * @return
     */
    public static long getPrevMeas1dTabTime(Calendar cal, long timems) {
        cal.clear();
        cal.setTime(new java.util.Date(timems));
        long rtn = -1;
        String currMeas1dTable = getMeas1dTabname(timems);
        String newMeas1dTable;
        int incr = -10;
        do {
            cal.add(Calendar.DAY_OF_MONTH, incr);
            rtn = cal.getTimeInMillis();
            newMeas1dTable = getMeas1dTabname(rtn);
        } while (currMeas1dTable.equals(newMeas1dTable));
        return rtn;
    }

    public static String getMeas1hTabname(long timems) {
        Calendar cal = Calendar.getInstance();
        return getMeas1hTabname(cal, timems);
    }

    public static String getMeas6hTabname(long timems) {
        Calendar cal = Calendar.getInstance();
        return getMeas6hTabname(cal, timems);
    }

    public static String getMeas1dTabname(long timems) {
        Calendar cal = Calendar.getInstance();
        return getMeas1dTabname(cal, timems);
    }

    /**
     * 筛选在时间段范围内的表
     * @param begin
     * @param end
     * @param ranges
     * @return
     */
    private static String[] getRetTables(long begin, long end, List<MeasRange> ranges) {
        System.out.println("getRetTables start --------------------------------------------------");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("begin:" + dateFormat.format(new Date(begin)));
        System.out.println("end:" + dateFormat.format(new Date(end)));
        String[] tables = new String[ranges.size()];
        int i = 0;
        for (MeasRange range : ranges) {
            long rBegin = range.getMinTimestamp();
            long rEnd = range.getMaxTimestamp();
            System.out.println("rBegin:" + dateFormat.format(new Date(rBegin)));
            System.out.println("rEnd:" + dateFormat.format(new Date(rEnd)));
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
        System.out.println("getRetTables start --------------------------------------------------");
        return retTables;
    }

    private static void setMeas1hRanges() {
        System.out.println("setMeas1hRanges start --------------------------------------------------");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long startTime = System.currentTimeMillis();
        String currMeas1hTable = getMeas1hTabname(cal, startTime);
        startTime = getFirstDayOfWeek(startTime);
        String meas1hTable = currMeas1hTable;
        long endTime = getLastDayOfWeek(startTime);
        System.out.println("startTime:" + dateFormat.format(new Date(startTime)));
        System.out.println("endTime:" + dateFormat.format(new Date(endTime)));
        System.out.println("Meas1hTable:" + meas1hTable);
        MeasRange range = new MeasRange(currMeas1hTable, startTime, endTime);
        synchronized (_meas1hRanges) {
            do {
                _meas1hRanges.add(range);
                startTime = getPrevMeas1hTabTime(cal, startTime);
                startTime = getFirstDayOfWeek(startTime);
                endTime = getLastDayOfWeek(startTime);
                meas1hTable = getMeas1hTabname(cal, startTime);
                System.out.println("startTime:" + dateFormat.format(new Date(startTime)));
                System.out.println("endTime:" + dateFormat.format(new Date(endTime)));
                System.out.println("Meas1hTable:" + meas1hTable);
                range = new MeasRange(meas1hTable, startTime, endTime);
            } while (!currMeas1hTable.equals(meas1hTable));
            _meas1hUmRanges = Collections.unmodifiableList(_meas1hRanges);
        }
        System.out.println("setMeas1hRanges end ---------------------------------------------------");
    }

    private static void setMeas6hRanges() {
        System.out.println("setMeas6hRanges start --------------------------------------------------");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long startTime = System.currentTimeMillis();
        String currMeas6hTable = getMeas6hTabname(cal, startTime);
        startTime = getFirstDayOfTwoWeek(startTime);
        String meas6hTable = currMeas6hTable;
        long endTime = getLastDayOfTwoWeek(startTime);
        System.out.println("startTime:" + dateFormat.format(new Date(startTime)));
        System.out.println("endTime:" + dateFormat.format(new Date(endTime)));
        System.out.println("meas6hTable:" + meas6hTable);
        MeasRange range = new MeasRange(currMeas6hTable, startTime, endTime);
        synchronized (_meas6hRanges) {
            do {
                _meas6hRanges.add(range);
                startTime = getPrevMeas1hTabTime(cal, startTime);
                startTime = getFirstDayOfTwoWeek(startTime);
                endTime = getLastDayOfTwoWeek(startTime);
                meas6hTable = getMeas6hTabname(cal, startTime);
                System.out.println("startTime:" + dateFormat.format(new Date(startTime)));
                System.out.println("endTime:" + dateFormat.format(new Date(endTime)));
                System.out.println("meas6hTable:" + meas6hTable);
                range = new MeasRange(meas6hTable, startTime, endTime);
            } while (!currMeas6hTable.equals(meas6hTable));
            _meas6hUmRanges = Collections.unmodifiableList(_meas6hRanges);
        }
        System.out.println("setMeas6hRanges end ---------------------------------------------------");
    }

    private static void setMeas1dRanges() {
        System.out.println("setMeas1dRanges start --------------------------------------------------");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long startTime = System.currentTimeMillis();
        String currMeas1dTable = getMeas1dTabname(cal, startTime);
        startTime = getFirstDayOfMonth(startTime);
        String meas1dTable = currMeas1dTable;
        long endTime = getLastDayOfMonth(startTime);
        System.out.println("startTime:" + dateFormat.format(new Date(startTime)));
        System.out.println("endTime:" + dateFormat.format(new Date(endTime)));
        System.out.println("meas1dTable:" + meas1dTable);
        MeasRange range = new MeasRange(currMeas1dTable, startTime, endTime);
        synchronized (_meas1dRanges) {
            do {
                _meas1dRanges.add(range);
                startTime = getPrevMeas1hTabTime(cal, startTime);
                startTime = getFirstDayOfMonth(startTime);
                endTime = getLastDayOfMonth(startTime);
                meas1dTable = getMeas1dTabname(cal, startTime);
                System.out.println("startTime:" + dateFormat.format(new Date(startTime)));
                System.out.println("endTime:" + dateFormat.format(new Date(endTime)));
                System.out.println("meas1dTable:" + meas1dTable);
                range = new MeasRange(meas1dTable, startTime, endTime);
            } while (!currMeas1dTable.equals(meas1dTable));
            _meas1dUmRanges = Collections.unmodifiableList(_meas1dRanges);
        }
        System.out.println("setMeas1dRanges end ---------------------------------------------------");
    }

    public static void getWeeks() {
        System.out.println("getWeeks-----------------------------------------------------");
        Calendar cal = Calendar.getInstance();
        // -----------------------------------------------------
        // Calendar calCur = Calendar.getInstance();
        // calCur.set(2007, 0, 1, 0, 0);
        // long currTime = calCur.getTimeInMillis();
        // -----------------------------------------------------
        long currTime = System.currentTimeMillis();
        // -----------------------------------------------------
        int weekofperiod = getWeekOfPeriod(cal, currTime);
        System.out.println("weekofperiod:" + weekofperiod);
        // -----------------------------------------------------
        int weekTable = weekofperiod % NUMBER_OF_MEAS_1H_TABLES;
        System.out.println("weekTable:" + weekTable);
        System.out.println(MEAS_1H_TABLE + "_" + weekTable + "w");
        String meas1hTabname = getMeas1hTabname(cal, currTime);
        System.out.println("meas1hTabname:" + meas1hTabname);
        // -----------------------------------------------------
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long firstDayOfWeek = getFirstDayOfWeek(currTime);
        long lastDayOfWeek = getLastDayOfWeek(currTime);
        System.out.println("firstDayOfWeek:" + sdf.format(new Date(firstDayOfWeek)));
        System.out.println("lastDayOfWeek:" + sdf.format(new Date(lastDayOfWeek)));
        // -----------------------------------------------------
        long preTime = getPrevMeas1hTabTime(cal, currTime);
        String meas1hPreTabname = getMeas1hTabname(cal, preTime);
        long firstPreDayOfWeek = getFirstDayOfWeek(preTime);
        long lastPreDayOfWeek = getLastDayOfWeek(preTime);
        System.out.println("meas1hPreTabname:" + meas1hPreTabname);
        System.out.println("firstPreDayOfWeek:" + sdf.format(new Date(firstPreDayOfWeek)));
        System.out.println("lastPreDayOfWeek:" + sdf.format(new Date(lastPreDayOfWeek)));
    }

    public static void getTwoWeeks() {
        System.out.println("getTwoWeeks-----------------------------------------------------");
        Calendar cal = Calendar.getInstance();
        // -----------------------------------------------------
        Calendar calCur = Calendar.getInstance();
        calCur.set(2007, 0, 1, 0, 0);
        long currTime = calCur.getTimeInMillis();
        // -----------------------------------------------------
        // long currTime = System.currentTimeMillis();
        // -----------------------------------------------------
        int twoWeekofperiod = getTwoWeekOfPeriod(cal, currTime);
        System.out.println("twoWeekofperiod:" + twoWeekofperiod);
        // -----------------------------------------------------
        int twoWeekTable = twoWeekofperiod % NUMBER_OF_MEAS_6H_TABLES;
        System.out.println("twoWeekTable:" + twoWeekTable);
        System.out.println(MEAS_6H_TABLE + "_" + twoWeekTable + "tw");
        String meas6hTabname = getMeas6hTabname(cal, currTime);
        System.out.println("meas1hTabname:" + meas6hTabname);
        // -----------------------------------------------------
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long firstDayOfTwoWeek = getFirstDayOfTwoWeek(currTime);
        long lastDayOfTwoWeek = getLastDayOfTwoWeek(currTime);
        System.out.println("firstDayOfTwoWeek:" + sdf.format(new Date(firstDayOfTwoWeek)));
        System.out.println("lastDayOfTwoWeek:" + sdf.format(new Date(lastDayOfTwoWeek)));
        // -----------------------------------------------------
        long preTime = getPrevMeas6hTabTime(cal, currTime);
        String meas6hPreTabname = getMeas6hTabname(cal, preTime);
        long firstPreDayOfTwoWeek = getFirstDayOfTwoWeek(preTime);
        long lastPreDayOfTwoWeek = getLastDayOfTwoWeek(preTime);
        System.out.println("meas6hPreTabname:" + meas6hPreTabname);
        System.out.println("firstPreDayOfTwoWeek:" + sdf.format(new Date(firstPreDayOfTwoWeek)));
        System.out.println("lastPreDayOfTwoWeek:" + sdf.format(new Date(lastPreDayOfTwoWeek)));
    }

    public static void getMonths() {
        System.out.println("getMonths-----------------------------------------------------");
        Calendar cal = Calendar.getInstance();
        // -----------------------------------------------------
        Calendar calCur = Calendar.getInstance();
        calCur.set(2007, 0, 1, 0, 0);
        long currTime = calCur.getTimeInMillis();
        // -----------------------------------------------------
        // long currTime = System.currentTimeMillis();
        // -----------------------------------------------------
        int monthofperiod = getMonthOfPeriod(cal, currTime);
        System.out.println("monthofperiod:" + monthofperiod);
        // -----------------------------------------------------
        int monthTable = monthofperiod % NUMBER_OF_MEAS_1D_TABLES;
        System.out.println("monthTable:" + monthTable);
        System.out.println(MEAS_1D_TABLE + "_" + monthTable + "m");
        String meas1dTabname = getMeas1dTabname(cal, currTime);
        System.out.println("meas1hTabname:" + meas1dTabname);
        // -----------------------------------------------------
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long firstDayOfMonth = getFirstDayOfMonth(currTime);
        long lastDayOfMonth = getLastDayOfMonth(currTime);
        System.out.println("firstDayOfWeek:" + sdf.format(new Date(firstDayOfMonth)));
        System.out.println("lastDayOfWeek:" + sdf.format(new Date(lastDayOfMonth)));
        // -----------------------------------------------------
        long preTime = getPrevMeas1dTabTime(cal, currTime);
        String meas1dPreTabname = getMeas1dTabname(cal, preTime);
        long firstPreDayOfMonth = getFirstDayOfMonth(preTime);
        long lastPreDayOfMonth = getLastDayOfMonth(preTime);
        System.out.println("meas1dPreTabname:" + meas1dPreTabname);
        System.out.println("firstPreDayOfMonth:" + sdf.format(new Date(firstPreDayOfMonth)));
        System.out.println("lastPreDayOfMonth:" + sdf.format(new Date(lastPreDayOfMonth)));
    }

    public static void main(String[] args) {
        System.out.println("main start -------------------------------------------------------------");
        getWeeks();
        getTwoWeeks();
        getMonths();
        setMeas1hRanges();
        setMeas6hRanges();
        setMeas1dRanges();
        Calendar calBegin = Calendar.getInstance();
        calBegin.set(2012, 8, 18, 18, 18);
        Calendar calEnd = Calendar.getInstance();
        calEnd.set(2012, 9, 18, 18, 18);
        long begin = calBegin.getTimeInMillis();
        long end = calEnd.getTimeInMillis();
        String[] meas1hTables = getRetTables(begin, end, _meas1hUmRanges);
        for (String table : meas1hTables) {
            System.out.println("meas1hTables:" + table);
        }
        String[] meas6hTables = getRetTables(begin, end, _meas6hUmRanges);
        for (String table : meas6hTables) {
            System.out.println("meas6hTables:" + table);
        }
        String[] meas1dTables = getRetTables(begin, end, _meas1dUmRanges);
        for (String table : meas1dTables) {
            System.out.println("meas1dTables:" + table);
        }
        System.out.println("main end -------------------------------------------------------------");
    }
}
