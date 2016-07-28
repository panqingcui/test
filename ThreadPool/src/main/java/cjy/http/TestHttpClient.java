/*
 * Copyright (c) 2010-2015 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2015湖南蚁坊软件有限公司。保留所有权利
 */
package cjy.http;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 *
 * 创建日期 2016年7月20日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class TestHttpClient {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("realm", "36v8tudu9Z");
        headers.put("remote_addr", "192.168.1.0");
        headers.put("user_ip", "127.0.0.1");
        headers.put("Authorization", "oauth2 4j7Oxi8BJ5iXaY00uTrHieRK");
        headers.put("token", "15YCmRh2x4ZybWA0wpTWXALv");
        headers.put("userId", "3722pgujaa36to1mmu11Abkbn0rH4oGbux0zVCN1mIa");
        headers.put("user_id", "3722pgujaa36to1mmu11Abkbn0rH4oGbux0zVCN1mIa");
        headers.put("Content-Type", "application/json");
        // headers.put("charset", HTTP.UTF_8);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customer_id", "36v91l0mrP*");
        // headers.put(READ_TIME_OUT, READ_TIMEOUT);
        // String
        // params="{"apps":["36v91l0mrP36q65t2ns2tmcYh4rJ5fpaIh0HYFe51JG"],"customer_id":"36v91l0mrP*","sale_stages":"试用阶段"}";
        String result = new HttpClientServiceImpl().sendHttpPost(
                "http://172.19.104.108:8182/rest/customer/v1/salesopportunity/query/10/1",
                "{\"apps\":[\"36v91l0mrP36q65t2ns2tmcYh4rJ5fpaIh0HYFe51JG\"],\"customer_id\":\"36v91l0mrP*\",\"sale_stages\":\"试用阶段\"}",
                headers);
        // String result = new
        // HttpClientServiceImpl().sendHttpGet("http://172.19.83.94:8181/rest/configer?nodeName=/测试",
        // headers);
        // JSONObject object = new JSONObject();
        // List<String> list = new ArrayList<>();
        // String s = new String("数据库地址".getBytes("UTF-8"));
        // list.add("mysql.url=" + s);
        // object.put("nodeName", "/antfact/test/1.0/mysql");
        // object.put("applicationName", "测试".getBytes("UTF-8"));
        // object.put("fileCotents", list);
        // String result = new
        // HttpClientServiceImpl().sendHttpPost("http://172.19.83.94:8181/rest/configer",
        // object.toJSONString(), headers);
        System.out.println(result);
    }
}
