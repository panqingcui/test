package cjy.com.wzjc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestUrlConnection {
    public static void main(String[] args) {
        HttpURLConnection conn = null;
        try {
            // 新建一个URL对象，指定到请求的url.
            // URL url = new URL("http://www.taobao.com");
            URL url = new URL("http://localhost:8080/rest-spring/jsonfeed/cjy.json");
            conn = (HttpURLConnection) url.openConnection();
            // conn.setRequestMethod("POST");
            System.out.println("statecode:" + conn.getResponseCode()); // 得到服务器端响应码
            System.out.println("Type:" + conn.getContentType()); // 得到网页文件类型
            System.out.println("Length:" + conn.getContentLength());
            System.out.println("Time:" + conn.getExpiration());
            System.out.println("Time:" + conn.getReadTimeout());
            System.out.println("LastModified:" + conn.getLastModified());
            InputStream in = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String tempLine = rd.readLine();
            StringBuffer temp = new StringBuffer();
            while (tempLine != null) {
                temp.append(tempLine);
                tempLine = rd.readLine();
            }
            String responseContent = temp.toString();
            System.out.println(responseContent);
            rd.close();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}
