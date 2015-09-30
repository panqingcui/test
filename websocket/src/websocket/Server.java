/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年8月7日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);
        SocketIOServer server = new SocketIOServer(config);
        CharteventListener listner = new CharteventListener();
        listner.setServer(server);
        // chatevent为事件名称
        server.addEventListener("chatevent", String.class, listner);
        // 启动服务
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }
}
