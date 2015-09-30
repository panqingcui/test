/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd.  All rights reserved.
 * 版权所有(c) 2013-2014 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年2月16日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Servicer implements Runnable {
    Socket socket;

    public Servicer(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            while (true) {
                String strWord = bufferedReader.readLine();
                // System.out.println(strWord+":"+strWord.length());
                if ("quit".equalsIgnoreCase(strWord)) {
                    break;
                }
                String strEcho = (new StringBuilder(strWord).reverse()).toString();
                // dataOutputStream.writeBytes(strWord+"---->"+strEcho+"\r\n");
                // System.getProperty("line.separator")为跨平台换行符。Window为\r\n,unix为\n
                dataOutputStream.writeBytes(strWord + "---->" + strEcho + System.getProperty("line.separator"));
            }
            bufferedReader.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5140);
        Socket socket = serverSocket.accept();// 阻塞等待消息
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }
}
