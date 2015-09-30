package cjy.com.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

/**
 * 
 * <p>java 线程池 固定线程数测试。<p>
 * 
 * 创建日期 2013年12月26日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class newFixedThreadPool {
    private static Logger logger = Logger.getLogger(newFixedThreadPool.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);// 开启线程 设置3个线程缓冲
        @SuppressWarnings("rawtypes")
        List<Future> futrues = new ArrayList<Future>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ThreadRun t = new ThreadRun();
            futrues.add(executor.submit(t));// 提交任务
            // logger.info("执行的任务数：" + i);
        }
        for (Future futrue : futrues) {
            futrue.get();// 线程任务已经完成
        }
        Runnable run = new Runnable() {
            public void run() {
                System.out.println("返回值：");
            }
        };
        Future future = null;
        future = executor.submit(run);// 提交第二个任务
        future.get();
        executor.shutdown();
        // executor.awaitTermination(1000, TimeUnit.DAYS);
        // Date date = new Date(end - start);
        // Calendar c = Calendar.getInstance();
        // c.setTimeInMillis(end - start);
        long end = System.currentTimeMillis();
        logger.info("" + (end - start) + "毫秒");
        ThreadRun t = new ThreadRun();
        // Set<Long> s = t.getThreadCount();
        // Iterator<Long> it = s.iterator();
        // int count = 0;
        // while (it.hasNext()) {
        // Long l = it.next();
        // count++;
        // }
        // logger.info("开启线程数：" + t.getThreadCount() + " " + t.getThreadCount().size() + "个");
        logger.info("开启线程数：" + t.getThreadCount().size());
    }
}
