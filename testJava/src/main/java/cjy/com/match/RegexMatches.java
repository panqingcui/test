/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.match;

import java.util.regex.Pattern;

/**
 * <p>下面的例子说明如何从一个给定的字符串中找到数字串：。<p>
 * 
 * 创建日期 2014年12月16日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class RegexMatches {
    public static void main(String[] args) {
        // // 按指定模式在字符串查找
        // String line = "This order was placed for QT3000! OK?";
        // String pattern = "(.*)([0-9])(.*)";
        // // 创建 Pattern 对象
        // Pattern r = Pattern.compile(pattern);
        // // 现在创建 matcher 对象
        // Matcher m = r.matcher(line);
        // if (m.find()) {
        // System.out.println("Found value: " + m.group(0));
        // System.out.println("Found value: " + m.group(1));
        // System.out.println("Found value: " + m.group(2));
        // System.out.println(m.groupCount());
        // } else {
        // System.out.println("NO MATCH");
        // }
        // String str = "0:00:sf:sf:ac:ba";
        // str.replace("[0-9]:[0-9]", "00:");
        // String p = "";
        // String str = "0:20:00:af:01:0:0";
        // String regex = "\\b0\\b"; // 取由四个字符组成的单词
        // Pattern pattern = Pattern.compile("0");
        // String[] s = str.split(":");
        // for (String r : s) {
        // if (r.length() == 1) {
        // Matcher matcher = pattern.matcher(r);
        // r = matcher.replaceAll("00");
        // }
        // }
        String str = "0:20:00:af:01:0:0";
        String[] s = str.split("\\b");
        for (String s1 : s) {
            System.out.println(s1.toString());
        }
        String regex = "\\b0\\b";
        Pattern pattern = Pattern.compile(regex);
        String matcher = pattern.matcher(str).replaceAll("00");
        // 00:20:00:af:01:00:00
        System.out.println(matcher);
    }
}
