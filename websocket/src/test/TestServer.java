/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年8月12日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class TestServer {
    public void start() {
        try {
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("start to accept...");
            Socket socket = ss.accept();
            // 建立输入流
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Object obj = ois.readObject();
            if (obj != null) {
                User user = (User) obj;// 把接收到的对象转化为user
                System.out.println("user: " + user.getName());
                System.out.println("password: " + user.getPassword());
            }
            OutputStream os = socket.getOutputStream();
            os.write("ceshi".getBytes());
            Thread.sleep(100000);
            ois.close();
            socket.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestServer().start();
    }
}
