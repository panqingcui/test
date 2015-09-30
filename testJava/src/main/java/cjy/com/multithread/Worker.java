package cjy.com.multithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Worker extends Thread {
    // private final Lock reentrantLock = new ReentrantLock();
    private static final Lock reentrantLock = new ReentrantLock();
    private String name;

    public Worker(String name) {
        this.name = name;
    }

    public void executeJob() {
        try {
            reentrantLock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println(name + "-" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public void run() {
        executeJob();
    }

    public static void main(String args[]) {
        new Worker("thread-1").start();
        new Worker("thread-2").start();
    }
}
