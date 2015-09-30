/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package socket;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年8月11日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class Client {
    public static void main(String args[]) throws Exception {
        // 为了简单起见，所有的异常都直接往外抛
        String host = "192.168.2.176"; // 要连接的服务端IP地址
        int port = 9092; // 要连接的服务端对应的监听端口
        // 与服务端建立连接
        Socket client = new Socket(host, port);
        while (true) {
            try {
                DataInputStream in = new DataInputStream(client.getInputStream());
                byte[] bytes = new byte[1000];
                in.read(bytes);
                String s = new String(bytes);
                System.out.println(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
