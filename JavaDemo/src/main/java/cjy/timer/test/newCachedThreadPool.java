package cjy.timer.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * 
 * <p>创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程 ,线程池无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。<p>
 * 
 * 创建日期 2014年1月5日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class newCachedThreadPool {
    private static Logger logger = Logger.getLogger(newCachedThreadPool.class);

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();// 无限制 线程池
        for (int i = 0; i < 5; i++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("newCachedThreadPool线程池");
                    ;
                }
            };
            service.submit(r);
        }
        service.shutdown();
    }
}
