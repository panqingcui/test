// package com.offcial;
//
// public class AccessToken {
// private static DateTime GetAccessToken_Time;
// // / <summary>
// // / 过期时间为7200秒
// // / </summary>
// private static int Expires_Period = 7200;
// // / <summary>
// // /
// // / </summary>
// private static String mAccessToken;
//
// // / <summary>
// // /
// // / </summary>
// // / <summary>
// // / 判断Access_token是否过期
// // / </summary>
// // / <returns>bool</returns>
// private static bool HasExpired() {
// if (GetAccessToken_Time != null) {
// // 过期时间，允许有一定的误差，一分钟。获取时间消耗
// if (DateTime.Now > GetAccessToken_Time.AddSeconds(Expires_Period).AddSeconds(-60)) {
// return true;
// }
// }
// return false;
// }
// }
