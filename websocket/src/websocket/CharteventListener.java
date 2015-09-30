/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package websocket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年8月7日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class CharteventListener implements DataListener<String> {
    SocketIOServer server;
    public static SocketIOClient client;

    public void setServer(SocketIOServer server) {
        this.server = server;
    }

    public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
        // chatevent为 事件的名称， data为发送的内容
        System.out.println("发送消息" + data);
        this.server.getBroadcastOperations().sendEvent("chatevent", data);
        // client.sendEvent("chatevent", data);
        client.sendEvent("chatevent", data);
        CharteventListener.client = client;
    }
}
