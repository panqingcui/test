package cjy.timer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTool {
    private static ExecutorService service = Executors.newCachedThreadPool();
    private static ExecutorService servicea = Executors.newSingleThreadExecutor();
    private static ThreadPoolExecutor service1 = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(300));

    public static ExecutorService getService() {
        return service;
    }
}
