/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.timer.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年1月14日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
class PausableThreadPoolExecutor extends ThreadPoolExecutor {
    private static Logger logger = Logger.getLogger(PausableThreadPoolExecutordemo_001.class);

    public PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected void beforeExecute(Thread t, Runnable r) {
        // super.afterExecute(r, t);
        logger.info("方法执行前");
    }

    protected void afterExecute(Runnable r, Throwable t) {
        // super.afterExecute(r, t);
        logger.info("方法执行后");
    }
}

public class PausableThreadPoolExecutordemo_001 {
    public static void main(String[] args) {
        PausableThreadPoolExecutor pausable = new PausableThreadPoolExecutor(0, Integer.MAX_VALUE, 60L,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            pausable.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("启动线程.....");
                }
            });
        }
        // pausable.shutdown();
    }
}
