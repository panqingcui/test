package cjy.com.wzjc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyTest {
    public static void main(String[] args) {
        try {
            long begintime = System.currentTimeMillis();
            FileReader fr = new FileReader("C://Users/macbook pro/Desktop/URL.txt");
            FileWriter fw = new FileWriter("C://Users/macbook pro/Desktop/response_time.txt");
            BufferedReader br = new BufferedReader(fr);
            BufferedWriter bw = new BufferedWriter(fw);
            String s;
            int count = 0;
            while ((s = br.readLine()) != null) {
                count++;
                System.out.println("URL is: " + s);
                // 网站链接
                URL url = new URL(s);
                HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
                urlcon.connect();
                // 获取网站相关内容，
                InputStream is = urlcon.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                StringBuffer bs = new StringBuffer();
                String l = null;
                int i = 0; // 读7行
                while (i != 7) {
                    l = buffer.readLine();
                    i++;
                    bs.append(l).append("\n");
                }
                System.out.print(bs.toString());
                // 为每个URL创建不同的文件并将网站内容写入
                File file = new File("C://Users/macbook pro/Desktop/" + count + ".txt");
                FileWriter fw1 = new FileWriter("C://Users/macbook pro/Desktop/" + count + ".txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);
                bw1.write("网页：" + s + "的相关内容是：\n");
                bw1.newLine();
                bw1.write(bs.toString());
                bw1.newLine();
                bw1.write(" content-length：" + urlcon.getContentLength());
                bw1.newLine();
                bw1.write(" content-type：" + urlcon.getContentType());
                bw1.newLine();
                bw1.write(" date：" + urlcon.getDate());
                bw1.newLine();
                bw1.close();
                // 将网站响应时间写入response_time文件
                bw.write("URL:" + s + " 的响应时间是" + (System.currentTimeMillis() - begintime) + "毫秒\n");
                bw.newLine();
                // 打印响应时间
                System.out.println("响应时间为：" + (System.currentTimeMillis() - begintime) + "毫秒\n");
            } // end while
            bw.close();
            br.close();
            fr.close();
        }// end try
        catch (MalformedURLException e) {
            //
        } catch (IOException e) {
            // System.out.println(e);
        }
    }
}
