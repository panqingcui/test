package com.offcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.util.AccessToken;
import com.util.MyX509TrustManager;

public class Official {
    /**
     * 主动推送信息接口 SENDMSGURL
     */
    private final static String SENDMSGURL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    // 获取access_token的接口地址（GET） 限200（次/天）
    private final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private final static String USER_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid";
    private static Log log = LogFactory.getLog(Official.class);

    /**
     * 获取用户列表 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。一次拉取调用最多拉取10000个关注者的OpenID，
     * 可以通过多次拉取的方式来满足需求
     * @return
     * @throws IOException
     * @throws HttpException
     */
    // public static JSONArray getUserList(String token) {
    // try {
    // // String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid";
    // // HttpClient client = new HttpClient();
    // // Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
    // // Protocol.registerProtocol("https", myhttps);
    // // url = url.replace("ACCESS_TOKEN", token);
    // // GetMethod getMethod = new GetMethod(url);
    // // client.executeMethod(getMethod);
    // String url = USER_URL.replace("ACCESS_TOKEN", token);
    // JSONObject json = httpRequest(url, "GET", null);
    // // log.info("json:" + json.getJSONObject("data").getJSONArray("openid"));
    // System.out.println("json:" + json.getJSONObject("data").getJSONArray("openid"));
    // // String returnStr = getMethod.getResponseBodyAsString();
    // // JSONObject json = JSONObject.fromObject(returnStr);
    // // json.getJSONObject("data").getString("openId");
    // // RequestUserInfo loginJson = JSON.parseObject(json.toString(), RequestUserInfo.class);
    // // System.out.println("loginJson.getData().getOpenid():" + loginJson.getData().getOpenid());
    // return json.getJSONObject("data").getJSONArray("openid");
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // return null;
    // }
    /**
     * 请求获取 返回json格式的 access_token
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, Object user) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod) || "POST".equals(requestMethod))
                httpUrlConn.connect();
            // 当有数据需要提交时
            // if (null != outputStr) {
            // OutputStream outputStream = httpUrlConn.getOutputStream();
            // // 注意编码格式，防止中文乱码
            // outputStream.write(outputStr.getBytes("UTF-8"));
            // outputStream.close();
            // }
            if (null != user) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // String str =
                // " {'touser:'oDNgmuDtwkEFuCgHvvWvwPqxSI44','msgtype':'text','text':{'content':'Hello World' }}";
                String str = JSONObject.fromObject(user).toString();
                // JSONObject json =JSONObject.fromBean(str);
                // 注意编码格式，防止中文乱码
                // outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.write(str.getBytes());
                outputStream.close();
            }
            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
            System.out.println(jsonObject);
        } catch (ConnectException ce) {
            System.err.print(ce);
        } catch (Exception e) {
            System.err.print(e);
        }
        return jsonObject;
    }

    /**
     * @desc 推送信息
     * @param token
     * @param msg
     * @return
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static String sendMessage(String token, String requestMethod, Object user) {
        String url = SENDMSGURL.replace("ACCESS_TOKEN", token);
        httpRequest(url, requestMethod, user);
        // StringBuffer buffer = new StringBuffer();
        // JSONObject jsonObject = null;
        // TrustManager[] tm = {new MyX509TrustManager() };
        // SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        // sslContext.init(null, tm, new java.security.SecureRandom());
        // // 从上述SSLContext对象中得到SSLSocketFactory对象
        // SSLSocketFactory ssf = sslContext.getSocketFactory();
        // // sendMsgUrl = MessageFormat.format(sendMsgUrl, token);
        // sendMsgUrl = sendMsgUrl.replace("ACCESS_TOKEN", token);
        // URL url = new URL(sendMsgUrl);
        // HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
        // httpUrlConn.setSSLSocketFactory(ssf);
        // httpUrlConn.setDoOutput(true);
        // httpUrlConn.setDoInput(true);
        // httpUrlConn.setUseCaches(false);
        // // 设置请求方式（GET/POST）
        // httpUrlConn.setRequestMethod(requestMethod);
        // if ("POST".equalsIgnoreCase(requestMethod))
        // httpUrlConn.connect();
        // // 当有数据需要提交时
        // if (null != outputStr) {
        // OutputStream outputStream = httpUrlConn.getOutputStream();
        // String str = " {'touser:'oDNgmuDtwkEFuCgHvvWvwPqxSI44','msgtype':'text','text':{'content':'Hello World' }}";
        // WeChatUser user = new WeChatUser();
        // Content t = new Content();
        // t.setContent(outputStr);
        // user.setMsgtype("text");
        // user.setText(t);
        // user.setTouser("oDNgmuDtwkEFuCgHvvWvwPqxSI44");
        // str = JSONObject.fromObject(user).toString();
        // // JSONObject json =JSONObject.fromBean(str);
        // // 注意编码格式，防止中文乱码
        // // outputStream.write(outputStr.getBytes("UTF-8"));
        // outputStream.write(str.getBytes());
        // outputStream.close();
        // }
        // // 将返回的输入流转换成字符串
        // InputStream inputStream = httpUrlConn.getInputStream();
        // InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        // BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        // String str = null;
        // while ((str = bufferedReader.readLine()) != null) {
        // buffer.append(str);
        // }
        // bufferedReader.close();
        // inputStreamReader.close();
        // // 释放资源
        // inputStream.close();
        // inputStream = null;
        // httpUrlConn.disconnect();
        // jsonObject = JSONObject.fromObject(buffer.toString());
        // System.out.println(jsonObject);
        return null;
    }

    /**
     * 获取用户列表 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。一次拉取调用最多拉取10000个关注者的OpenID，
     * 可以通过多次拉取的方式来满足需求
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String[] getUserList(String token) {
        try {
            // String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid";
            // HttpClient client = new HttpClient();
            // Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
            // Protocol.registerProtocol("https", myhttps);
            // url = url.replace("ACCESS_TOKEN", token);
            // GetMethod getMethod = new GetMethod(url);
            // client.executeMethod(getMethod);
            String url = USER_URL.replace("ACCESS_TOKEN", token);
            JSONObject json = httpRequest(url, "GET", null);
            // String returnStr = getMethod.getResponseBodyAsString();
            // JSONObject json = JSONObject.fromObject(returnStr);
            RequestUserInfo loginJson = JSON.parseObject(json.toString(), RequestUserInfo.class);
            // System.out.println("loginJson.getData().getOpenid():" + loginJson.getData().getOpenid());
            return loginJson.getData().getOpenid();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // 获取accesstoken值
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.debug(jsonObject.getInt("errcode"));
                System.out.println("获取失败：" + jsonObject.getInt("errcode"));
            }
        }
        return accessToken;
    }

    public static void sendHttp(String f) throws KeyManagementException, NoSuchAlgorithmException,
            NoSuchProviderException, IOException {
        AccessToken token = getAccessToken("wxac985f1a80ee1803", "db13d5cd514c567d9e39d22c76826bfe");
        log.debug("token" + token.getToken());
        // sendMessage(token.getToken(), "GET", f);
    }

    /**
     * @desc 发起HTTP GET请求返回数据
     * @param url
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws KeyManagementException {
        // ResponseHandler responseHandler = new BasicResponseHandler();
        // String response = (String) this.webClient.execute(new HttpGet(url), responseHandler);
        // log.info("return response=====start======");
        // log.info(response);
        // log.info("return response=====end======");
        int a = 0;
        // while (true) {
        // try {
        // Thread.sleep(5000);
        // } catch (InterruptedException e1) {
        // // TODO Auto-generated catch block
        // e1.printStackTrace();
        // }
        AccessToken token = getAccessToken("wxac985f1a80ee1803", "db13d5cd514c567d9e39d22c76826bfe");
        // {"errmsg":"response out of time limit","errcode":45015}
        // 获取用户 openid
        String[] openidStr = getUserList(token.getToken());
        // for (int i = 0; i < openidStr.length; i++) {
        // System.out.println(openidStr[i]);
        // SendUser user = new SendUser();
        // Content t = new Content();
        // t.setContent("感谢您加入翱翔家族，会为您提供全方位服务！");
        // user.setMsgtype("text");
        // user.setText(t);
        // user.setTouser("oDNgmuDtwkEFuCgHvvWvwPqxSI44");
        SendXx user = new SendXx();
        user.setTouser("oDNgmuDtwkEFuCgHvvWvwPqxSI44");
        user.setMsgtype("news");
        Articles art = new Articles();
        Article ar = new Article();
        ar.setDescription("蚁巡");
        ar.setPicUrl("");
        ar.setTitle("蚁巡");
        ar.setUrl("");
        List<Article> list = new ArrayList<Article>();
        list.add(ar);
        art.setArticles(list);
        user.setNews(art);
        sendMessage(token.getToken(), "POST", user);
        // }
    }
}
