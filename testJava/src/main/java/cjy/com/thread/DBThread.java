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
 * <p>模拟入库线程。<p>
 * 
 * 创建日期 2013-6-17<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class DBThread extends Thread {
    // 日志对象
    private static Log log = LogFactory.getLog(DBThread.class);
    // 模拟处理数据
    private Integer rpt;

    public DBThread(Integer rpt) {
        this.rpt = rpt;
    }

    public void run() {
        try {
            log.info("DBThread 开始模拟处理 rpt" + rpt);
            Thread.sleep(20000);
            log.info("DBThread 模拟处理完成 rpt" + rpt);
        } catch (Exception e) {
            log.error("" + e);
        }
    }
}
