package cjy.timer.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 
 * <p>定时任务。<p>
 * 
 * 创建日期 2014年1月3日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class NewFuture {
    private static Logger logger = Logger.getLogger(NewFuture.class);
    int value = 0;
    int count = 0;//

    public static void main(String[] args) {
        try {
            new NewFuture().createRandomNumberFromMathRandom();
            // logger.info("和值：" + new NewTimerTask().getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * math随机生成
     * @throws InterruptedException
     */
    @SuppressWarnings("rawtypes")
    public void createRandomNumberFromMathRandom() throws InterruptedException {
        count++;// 定时器执行次数
        ExecutorService service = Executors.newCachedThreadPool();// 添加到线程池
        // ExecutorService service = Executors.newFixedThreadPool(3);
        logger.info("建立线程池...");
        List<Future> futures = new ArrayList<Future>();// 异步记录
        int sum = createRandomNumberFromRandom(); // 循环次数
        for (int i = 0; i < sum; i++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    int a = (int) Math.round((Math.random() * 10 + 1));
                    // logger.info("添加数字：" + a);
                    if (count > 1) {
                        setValue(0);
                        count = 1;
                    }
                    logger.info("开启线程，随机生成的数字:" + a + " ");
                    addValue(a);// 添加随机数据
                    // logger.info("存放值...");
                }
            };
            // futures.add(service.execute(r));
            futures.add(service.submit(r));// 添加到线程池
            // service.execute(r);
            // new Thread(r).start();
            // logger.info("添加到线程池...");
        }
        for (Future future : futures) {
            try {
                future.get(); // 进程执行完毕后
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        logger.info("线程池中的线程同步完成...");
        service.awaitTermination(5, TimeUnit.SECONDS);
        logger.info("得到的和值：" + getValue());
        service.shutdown();// 关闭线程池
    }

    @SuppressWarnings("static-access")
    public void addValue(int a) {
        this.value += a;
        // if ((count - 1) % 5 == 0) {
        // this.value = 0;// 清空
        // this.value += a;
        // }
        // count++;
    }

    public void setValue(int a) {
        this.value = a;
    }

    public int getValue() {
        return value;
    }

    /**
     * 通过Random生成随机数
     * @return
     */
    public int createRandomNumberFromRandom() {
        Random r = new Random();
        int a = r.nextInt(5) + 5;
        logger.info("执行：" + a + "次任务");
        return a;
    }
}
