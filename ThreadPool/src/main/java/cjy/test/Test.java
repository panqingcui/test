package cjy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Test {
    public static double test(String s) {
        String content = "";
        URL url = null;
        InputStream in = null;
        InputStreamReader bin = null;
        BufferedReader br = null;
        HttpURLConnection urlcon = null;
        try {
            url = new URL(s);
            urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setConnectTimeout(3000);
            urlcon.setReadTimeout(3000);
            in = urlcon.getInputStream();
            bin = new InputStreamReader(in);
            br = new BufferedReader(bin);
            String str = null;
            int i = 1;
            while ((str = br.readLine()) != null) {
                content = content + str;
                System.out.println(content);
                i++;
                if (i == 5) {
                    break;
                }
            }
            return 1.0;
        } catch (IOException e) {
            System.out.println(e + ">>>" + s);
            return 0.0;
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (bin != null)
                try {
                    bin.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (urlcon != null)
                urlcon.disconnect();
        }
    }

    public static void main(String[] args) {
        while (true) {
            String[] urs = {"http://172.19.121.68:8986/solr", "http://172.19.121.68:8983/solr",
                    "http://172.19.121.68:8984/solr" };
            for (String s : urs) {
                System.out.println(new Date() + ">>>>>>" + test(s));
            }
            ;
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
