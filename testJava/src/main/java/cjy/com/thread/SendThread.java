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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>测试发送线程。<p>
 * 
 * 创建日期 2013-6-17<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class SendThread extends Thread {
    // 日志对象
    private static Log log = LogFactory.getLog(SendThread.class);
    // 模拟发送数据
    private static Integer rpt = 1;
    // 接受测试线程
    private ReceiveThread receiveThread = new ReceiveThread();

    public void run() {
        receiveThread.start();
        while (true) {
            try {
                log.info("开始模拟发送 rpt=" + rpt);
                receiveThread.putEvent(rpt);
                Thread.sleep(500);
                if (rpt % 6 == 0) {
                    Thread.sleep(57000);
                }
                rpt++;
            } catch (Exception e) {
                log.error("测试发送线程出错: " + e);
            }
        }
    }
}
