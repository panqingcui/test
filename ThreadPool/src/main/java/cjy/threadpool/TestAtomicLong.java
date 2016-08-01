/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package cjy.threadpool;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 *
 * 创建日期 2016年8月1日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class TestAtomicLong {
    AtomicLong counter = new AtomicLong(0);

    public long count() {
        long result;
        boolean flag;
        System.out.println(counter.incrementAndGet());
        ;
        do {
            result = counter.get();
            // 断点
            // 单线程下, compareAndSet返回永远为true,
            // 多线程下, 在与result进行compare时, counter可能被其他线程set了新值, 这时需要重新再取一遍再比较,
            // 如果还是没有拿到最新的值, 则一直循环下去, 直到拿到最新的那个值
            flag = counter.compareAndSet(result, result + 1);
        } while (!flag);
        return result;
    }

    public static void main(String[] args) {
        final TestAtomicLong c = new TestAtomicLong();
        new Thread() {
            @Override
            public void run() {
                c.count();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                c.count();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                c.count();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                c.count();
            }
        }.start();
    }
}
