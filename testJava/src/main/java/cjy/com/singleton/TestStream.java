/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.singleton;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年10月27日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestStream {
    String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private TestStream() {}

    private static TestStream ts1 = null;

    public static TestStream getTest() {
        if (ts1 == null) {
            ts1 = new TestStream();
        }
        return ts1;
    }

    public void printInfo() {
        System.out.println("the name is " + name);
    }

    public static void main(String[] args) {
        TestStream ts1 = TestStream.getTest();
        ts1.setName("jason");
        ts1.printInfo();
        TestStream ts2 = TestStream.getTest();
        ts2.setName("0539");
        ts2.printInfo();
        if (ts1 == ts2) {
            System.out.println("创建的是同一个实例" + ts1.getName() + "," + ts2.getName());
        } else {
            System.out.println("创建的不是同一个实例");
        }
    }
}
