/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年8月11日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class Server {
    public static void main(String args[]) throws IOException {
        // 为了简单起见，所有的异常信息都往外抛
        int port = 8899;
        // 定义一个ServerSocket监听在端口8899上
        ServerSocket server = new ServerSocket(port);
        // server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
        while (true) {
            Socket socket = server.accept();
        }
    }
}
