// /*
// * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights
// reserved.
// * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
// */
// package webqq;
//
// /**
// * <p>功能描述,该部分必须以中文句号结尾。<p>
// *
// * 创建日期 2015年7月23日<br>
// * @author $Author$<br>
// * @version $Revision$ $Date$
// * @since 1.0.0
// */
//
// public class RequestBinTutorial {
// public static void main(String[] args) {
// HttpClient client = new HttpClient();
// GetMethod method = new GetMethod("http://requestb.in/1ku8fi21");
// try {
// int statusCode = client.executeMethod(method);
// byte[] responseBody = method.getResponseBody();
// System.out.println(new String(responseBody));
// } catch (Exception e) {
// System.err.println("Fatal error: " + e.getMessage());
// e.printStackTrace();
// } finally {
// method.releaseConnection();
// }
// }
// }
