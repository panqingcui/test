/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package cjy.com.thread;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>接受测试线程。<p>
 * 
 * 创建日期 2013-6-17<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class ReceiveThread extends Thread {
    // 日志对象
    private static Log log = LogFactory.getLog(ReceiveThread.class);
    // 锁对象
    private byte[] object = new byte[0];
    // 缓冲池
    private LinkedList<Integer> m_RunList = new LinkedList<Integer>();
    // 最大缓冲事件数
    private int batchCounts = 1000;

    public void run() {
        while (true) {
            try {
                Integer rpt = takeWork();
                log.info("开始模拟处理 rpt=" + rpt);
                Thread.sleep(20000);
                // DBThread dbThread = new DBThread(rpt);
                // dbThread.start();
                log.info("模拟处理 rpt=" + rpt + "完成");
            } catch (Exception e) {
                log.error("" + e);
            }
        }
    }

    /**
     * 缓存发送事件。
     * @param rpt 待处理的报告对象
     */
    public void putEvent(Integer rpt) {
        synchronized (object) {
            // 当分配的任务已满，则等待，否则继续分配任务
            while (m_RunList.size() >= batchCounts) {
                try {
                    log.info("报告池满，开始等待。");
                    long starttime = System.currentTimeMillis();
                    object.wait();
                    long endtime = System.currentTimeMillis();
                    long time = endtime - starttime;
                    log.info("继续报告处理，此轮等待时间：" + time);
                } catch (InterruptedException e) {
                    log.error(e);
                }
            }
            m_RunList.add(rpt);
            log.info("添加rpt=" + rpt + "后，当前队列大小为：" + m_RunList.size());
            object.notifyAll();
        }
    }

    /**
     * 获取待处理报告。
     * @return
     */
    public Integer takeWork() {
        Integer event = null;
        // 当处理的任务为空，则等待，否则继续处理任务
        synchronized (object) {
            while (m_RunList.size() == 0) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    log.error("" + e);
                }
            }
            // log.info(Thread.currentThread().getName() +
            // " take event 当前队列大小为：" + m_RunList.size());
            event = (Integer) m_RunList.removeLast();
            // log.info("模拟处理当前最早写入的数据 event=" + event);
            object.notifyAll();
        }
        return event;
    }
}
