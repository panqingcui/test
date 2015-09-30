/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved. 版权所有(c) 2013-2014
 * 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.kafka;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年12月22日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class AbstractZooKeeper implements Watcher {
    private static Log log = LogFactory.getLog(AbstractZooKeeper.class.getName());
    // 缓存时间
    private static final int SESSION_TIME = 2000;
    protected ZooKeeper zooKeeper;
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    public void connect(String hosts) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(hosts, SESSION_TIME, this);
        countDownLatch.await();
    }

    /*
     * (non-Javadoc)
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
     */
    @Override
    public void process(WatchedEvent event) {
        // TODO Auto-generated method stub
        if (event.getState() == KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}
