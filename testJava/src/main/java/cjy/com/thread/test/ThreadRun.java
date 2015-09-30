package cjy.com.thread.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * 
 * <p>测试线程。<p>
 * 
 * 创建日期 2013年12月26日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class ThreadRun extends Thread {
    private static Logger logger = Logger.getLogger(ThreadRun.class);
    // final static LockTest lockTest = new LockTest();
    static Set<Long> s = Collections.synchronizedSet(new HashSet()); // 线程安全
    // static Set<Long> s = new HashSet();
    Lock lock = new ReentrantLock();// 锁

    public void run() {
        logger.info("线程ID：" + Thread.currentThread().getId());
        add(Thread.currentThread().getId());
        // logger.info(s.size());
        // lockTest.addValue(10);
        // lockTest.getValue();
    }

    public Set getThreadCount() {
        return s;
    }

    public void add(Long i) {
        s.add(i);
    }
}
