/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年8月12日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class TestClient {
    public static void main(String[] args) {
        new TestClient().start();
    }

    public void start() {
        try {
            Socket socket = new Socket("127.0.0.1", 7777);
            // 建立输入流
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            User user = new User();
            user.setName("梁国俏");
            user.setPassword("123456");
            // 输入对象, 一定要flush（）
            oos.writeObject(user);
            oos.flush();
            while (true) {
                InputStream ips = socket.getInputStream();
                InputStreamReader ipsr = new InputStreamReader(ips);
                BufferedReader br = new BufferedReader(ipsr);
                String s = "";
                while ((s = br.readLine()) != null)
                    System.out.println(s);
                socket.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
