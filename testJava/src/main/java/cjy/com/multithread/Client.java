package cjy.com.multithread;

public class Client {
    /**
     * 
     * 启动并等待线程结束
     * 
     * @param threads
     * 
     * @throws InterruptedException
     * 
     * @Author YHJ create at 2013-4-26 下午05:57:16
     */
    private static void startAndWait(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads)
            thread.start();
        for (Thread thread : threads)
            thread.join();
    }

    /**
     * 
     * 准备线程数据
     * 
     * @param threads
     * 
     * @param testCase
     * 
     * @param threadCount
     * 
     * @param loopCount
     * 
     * @Author YHJ create at 2013-4-26 下午06:01:25
     */
    private static void prepare(Thread[] threads, final AtomicIntegerTestCase testCase, int threadCount,
            final int loopCount) {
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < loopCount; ++i) {
                        testCase.incrementAndGet();
                    }
                }
            };
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 前期数据准备
        final int threadCount = 200;// 线程数目
        final int loopCount = 100000;// 循环次数
        final AtomicIntegerWithLock lockTestCase = new AtomicIntegerWithLock();// 测试对象
        final AtomicIntegerWithSynchronized synchronizedtestCase = new AtomicIntegerWithSynchronized();
        // 第一波数据准备
        Thread[] threads = new Thread[threadCount];
        prepare(threads, lockTestCase, threadCount, loopCount);
        // 第一波启动
        long costTime = 0;
        long start = System.nanoTime();
        startAndWait(threads);
        long end = System.nanoTime();
        costTime = (end - start);
        // 第一波输出
        System.out.println("AtomicIntegerWithLock result:" + lockTestCase.get() + " costTime:" + costTime);
        System.out.println("=======我是分割线=======");
        // 第二波数据准备
        threads = new Thread[threadCount];
        prepare(threads, synchronizedtestCase, threadCount, loopCount);
        // 第二波启动
        costTime = 0;
        start = System.nanoTime();
        startAndWait(threads);
        end = System.nanoTime();
        costTime = (end - start);
        // 第二波输出
        System.out.println("AtomicIntegerWithSynchronized result:" + lockTestCase.get() + " costTime:" + costTime);
    }
}
