/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2013-2014 山东蚁巡网络科技有限公司。保留所有权利
 */
package cjy.com.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年1月5日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
// 70201327 18765875722
public class TestConnection {
    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException {
        try {
            new TestConnection().send();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void receive() {
        // http://api.duanxin.cm/?action=getBalance&username=用户账号&password=MD5位32密码
        StringBuilder builder = new StringBuilder("http://api.duanxin.cm/?action=getBalance");
        builder.append("&username=70201327");
        builder.append("&password=75e1251bf034f1addb7a3d42022c7e14");
        try {
            URL url = new URL(builder.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            System.out.println(br.readLine());
        } catch (MalformedURLException e) {} catch (IOException e) {}
    }

    public void send() throws IOException {
        String t = "pan_qcui的CPU利用率为百分之97, 不再健康范围内   pan_qcui设备无法访问";
        StringBuffer builder = new StringBuffer("http://api.duanxin.cm/?");
        builder.append("action=send&username=70201327");
        builder.append("&password=75e1251bf034f1addb7a3d42022c7e14");
        builder.append("&phone=");
        System.out.println(URLEncoder.encode(t));
        System.out.println(URLDecoder.decode(URLEncoder.encode(t)));
        builder.append("&content=" + URLEncoder.encode(t));
        builder.append("&encode=utf8");
        try {
            URL url = new URL(builder.toString());
            System.out.println(builder.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(connection.getInputStream()));
            System.out.println(br.readLine());
        } catch (MalformedURLException e) {
            System.err.print(e);
        } catch (IOException e) {
            System.err.print(e);
        }
        // try {
        // String requestUrl = "http://www.baidu.com";
        // StringBuffer buffer = null;
        // URL url = new URL(requestUrl);
        // HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
        // httpUrlConn.setDoInput(true);
        // httpUrlConn.setRequestMethod("GET");
        // // 获取输入流
        // InputStream inputStream = httpUrlConn.getInputStream();
        // InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
        // "utf-8");
        // BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        // // 读取返回结果
        // buffer = new StringBuffer();
        // String str = null;
        // while ((str = bufferedReader.readLine()) != null) {
        // buffer.append(str);
        // }
        // System.out.println(buffer);
        // // 释放资源
        // bufferedReader.close();
        // inputStreamReader.close();
        // inputStream.close();
        // httpUrlConn.disconnect();
        // } catch (Exception e) {
        // System.err.println(e);
        // }
    }
}
