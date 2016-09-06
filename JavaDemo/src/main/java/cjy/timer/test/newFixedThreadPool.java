package cjy.timer.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * 
 * <p>创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待<p>
 * 
 * 创建日期 2014年1月5日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class newFixedThreadPool {
    private static Logger logger = Logger.getLogger(newCachedThreadPool.class);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);// 3个线程
        for (int i = 0; i < 5; i++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("newFixedThreadPool线程池");
                    ;
                }
            };
            service.submit(r);
        }
        service.shutdown();
    }
}
