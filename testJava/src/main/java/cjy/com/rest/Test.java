// /*
// * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights
// reserved.
// * 版权所有(c) 2013-2014 山东蚁巡网络科技有限公司。保留所有权利
// */
// package cjy.com.rest;
//
// import java.io.BufferedReader;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.URL;
// import java.util.HashMap;
// import java.util.Map;
//
// /**
// * <p>功能描述,该部分必须以中文句号结尾。<p>
// *
// * 创建日期 2015年1月15日<br>
// * @author $Author$<br>
// * @version $Revision$ $Date$
// * @since 3.0.0
// */
// public class Test {
// public static void main(String[] args) throws MalformedURLException {
// new Test().addUser();
// }
//
// public void addUser() throws MalformedURLException {
// URL url = new URL("https://antfact.com/new_oauth/token/access_token");
// try {
// HttpURLConnection connection = (HttpURLConnection) url.openConnection();
// connection.setRequestMethod("POST");
// Map<String, String> map = new HashMap<String, String>();
// map.put("client_id", "3aueAnfXU4WMa7F0rLA1mCFA");
// map.put("client_secret", "2P6kDs6uN5fF8I903LDYfQ5n");
// connection.getOutputStream().write(map.toString().getBytes());
// InputStream in = connection.getInputStream();
// InputStreamReader isr = new InputStreamReader(in);
// BufferedReader br = new BufferedReader(isr);
// while (br.readLine() != -1) {}
// int state = connection.getResponseCode();
// if (state == 200) {
// System.out.println("连接正常");
// } else {
// System.out.println("连接失败");
// }
// } catch (Exception ex) {
// System.out.println("无法连接到在线客服：");
// }
// }
// }
