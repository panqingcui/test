package cjy.com.multithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @Described：带锁的测试
 * 
 * @author YHJ create at 2013-4-26 下午05:35:35
 * 
 * @ClassNmae com.yhj.lock.AtomicIntegerWithLock
 */
public class AtomicIntegerWithLock implements AtomicIntegerTestCase {
    private int value;
    private Lock lock = new ReentrantLock();

    @Override
    public int incrementAndGet() {
        try {
            lock.lock();
            return ++value;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int get() {
        try {
            lock.lock();
            return value;
        } finally {
            lock.unlock();
        }
    }
}
