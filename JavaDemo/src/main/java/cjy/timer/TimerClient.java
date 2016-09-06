package cjy.timer;

import java.util.Timer;

import org.apache.log4j.Logger;

/**
 * 
 * <p>定时器。<p>
 * 
 * 创建日期 2014年1月3日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TimerClient {
    private static Logger logger = Logger.getLogger(TimerClient.class);

    public static void main(String[] args) {
        run();
    }

    /**
     * 定时执行
     */
    public static void run() {
        Timer timer = new Timer();// 定时器
        NewTimerTask task = new NewTimerTask();// 定时任务
        logger.info("开启定时器 ：");
        timer.schedule(new TimerTaskDemo(), 0, 10000000);
        timer.schedule(new TimerTaskDemo(), 10000, 20000);
        System.gc();
        loop: while (true) {
            if (System.currentTimeMillis() - 1426060899335l > 1800000) {
                System.out.println("取消任务");
                timer.cancel();
                timer.schedule(new TimerTaskDemo(), 0, 10000000);
                timer.schedule(new TimerTaskDemo(), 10000, 20000);
                break loop;
            }
        }
        // timer.schedule(task2, 0, 10000);
    }
}
