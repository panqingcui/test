/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package cjy.com.wzjc;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>网站监测类。<p>
 * 
 * 创建日期 2012-12-26<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class UrlTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        HttpURLConnection urlcon = null;
        try {
            System.out.println("test url start");
            // String urlStr = "http://www.google.com.hk/";
            // String urlStr =
            // "http://video.baidu.com/v?ct=301989888&word=%D7%D4%D3%C9%B7%C9%CF%E8+%B7%EF%BB%CB%B4%AB%C6%E6";
            // String urlStr =
            // "http://video.baidu.com/s?f=1000&ty=music-6&word=%D5%C5%BD%DC%A1%B6%D2%B9%C3%A4%D6%A2%A1%B7%20-%20&"
            // +
            // "url=http%3A//www.56.com/w20/play_album-aid-10210178_vid-ODI3MjA1MTk.html";
            String urlStr = "http://antfact.com/eagletack/sms/send?mobile=13406980962&content=测试短信网关&access_token=3qpPmtd4a59P9PQ0fSVvkiPC";
            // String urlStr = "http://video.baidu.com";
            // 网站链接
            URL url = new URL(urlStr);
            long beginTime = System.currentTimeMillis();
            urlcon = (HttpURLConnection) url.openConnection();
            urlcon.connect();
            int responseCode = urlcon.getResponseCode();
            System.out.println("responseCode:" + responseCode);
            String responseMessage = urlcon.getResponseMessage();
            System.out.println("responseMessage:" + responseMessage);
            long endTime = System.currentTimeMillis();
            System.out.println("response time:" + (endTime - beginTime) + "ms");
            // int connectTimeout = urlcon.getConnectTimeout();
            // System.out.println("connectTimeout:" + connectTimeout);
            // long expires = urlcon.getExpiration();
            // System.out.println("expires:" + expires);
            // int readTimeout = urlcon.getReadTimeout();
            // System.out.println("readTimeout:" + readTimeout);
            System.out.println("test url end");
        } catch (Exception e) {
            System.err.println("test url error");
        } finally {
            if (urlcon != null) {
                urlcon.disconnect();
            }
        }
    }
}
