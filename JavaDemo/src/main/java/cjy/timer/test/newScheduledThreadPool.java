package cjy.timer.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 
 * <p>创建一个定长线程池，支持定时及周期性任务执行。<p>
 * 
 * 创建日期 2014年1月5日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class newScheduledThreadPool {
    private static Logger logger = Logger.getLogger(newScheduledThreadPool.class);

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                logger.info("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
