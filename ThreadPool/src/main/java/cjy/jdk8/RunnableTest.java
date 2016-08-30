/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package cjy.jdk8;

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
        while (true) {
            new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
        }
    }
}
