/*
 * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2013-2014 山东蚁巡网络科技有限公司。保留所有权利
 */
package webqq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年5月15日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class Webqq {
    public static void main(String[] args) {
        String msg = "告警设备及服务:\r\n";
        msg += "qlibm的每分钟总流入量为2.23MB\r\n";
        msg += "vm-pan-PC的磁盘利用率为百分之51.4\r\n";
        msg += "xenserver-80的CPU利用率为百分之4.3";
        msg += "T240的平均负载为4.25";
        msg += "DEMO的LISTEN为36个";
        msg += "NFS/FTP的每分钟总流入量为719.59KB";
        msg += "antrol-231的CPU利用率为百分之100";
        msg += "T240的CPU利用率为百分之100";
        msg += "qlibm的CPU利用率为百分之10";
        send(msg);
    }

    public static void send(String msg) {
        String url = "http://172.19.105.60:8000/api/sendMsg";
        HttpURLConnection conn = null;
        BufferedReader br = null;
        try {
            if (msg != null && msg.length() > 0) {
                msg = URLEncoder.encode(msg, "utf-8");
            }
            String httpUrl = url + "?msg=" + msg;
            URL u = new URL(httpUrl);
            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
