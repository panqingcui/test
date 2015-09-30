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
 * <p>java线程池自动回收。<p>
 * 
 * 创建日期 2013年12月26日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class newCachedThreadPool {
    private static Logger logger = Logger.getLogger(newCachedThreadPool.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 不过这段代码稍微有点复杂，而且有不足的地方。如果第一个任务耗费非常长的时间来执行，然后其他的任务都早于它结束，那么当前线程就无法在第一个任务结束之前获得执行结果，但是别着急，Java
        // 为你提供了解决方案——CompletionService。
        // 一个 CompletionService 就是一个服务，用以简化等待任务的执行结果，实现的类是 ExecutorCompletionService，该类基于 ExecutorService，因此我们可试试下面的代码
        ExecutorService executor = Executors.newCachedThreadPool();// 创建java 线程池
        // CompletionService<String> pool = new ExecutorCompletionService(executor);
        long start = System.currentTimeMillis();
        List<Future> futrues = new ArrayList<Future>(); // 存放线程完毕
        for (int i = 0; i < 100; i++) {
            ThreadRun run = new ThreadRun();
            futrues.add(executor.submit(run));
        }
        for (Future future : futrues) {
            future.get();// 线程执行完毕
        }
        Future future = null;
        Runnable run = new Runnable() {
            // final LockTest lockTest = new LockTest();
            public void run() {
                System.out.println("返回值：");
            }
        };
        future = executor.submit(run);// 提交第二个任务
        future.get();// 第二人任务完成
        executor.shutdown();
        long end = System.currentTimeMillis();
        logger.info((end - start) + "毫秒");
        ThreadRun r = new ThreadRun();
        // 记录 线程数，因set size 返回值 有问题
        // Set s = r.getThreadCount();
        // Iterator it = s.iterator();
        // int count = 0;
        // while (it.hasNext()) {
        // Long l = (Long) it.next();
        // count++;
        // }
        logger.info("开启线程数：" + r.getThreadCount().size());
    }
}
