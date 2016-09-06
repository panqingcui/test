package cjy.timer.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 
 * <p>ThreadPoolExecutor核心类测试<p>
 * 
 * 创建日期 2014年1月5日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class ThreadPoolExecutor_demo_001 {
    private static Logger logger = Logger.getLogger(ThreadPoolExecutor_demo_001.class);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(2),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            final int a = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("hello" + a);
                }
            };
            executor.submit(r);
        }
        executor.shutdown();
        logger.info("线程池在运行过程中已完成的任务数量: " + executor.getCompletedTaskCount());
        logger.info("线程池曾经创建过的最大线程数量: " + executor.getLargestPoolSize());
        logger.info("获取活动的线程数: " + executor.getActiveCount());
    }
}
