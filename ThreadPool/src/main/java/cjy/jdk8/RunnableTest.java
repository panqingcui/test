/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package cjy.jdk8;

import java.util.Arrays;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2016年2月14日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class RunnableTest {
    public static void main(String[] args) {
        // JButton btn = new JButton();
        // btn.addActionListener(
        // e -> System.out.println("Event Source is: " + e.getSource())
        // );
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
        Arrays.asList("a", "c", "d").forEach((String a) -> System.out.print(a));
        // Arrays.asList("a", "b", "c").forEach(action);
    }
}
