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
package cjy.com.string;

/**
 * <p>测试字符串拆分。<p>
 * 
 * 创建日期 2013-7-2<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestString {
    public static void main(String[] args) {
        // String measStatus = "||";
        // String measStatus = "a||";
        String measStatus = "a|b|";
        // String measStatus = "a|b|c";
        // String measStatus = "a,b,c|d,e|f,g,h";
        String[] measStatusArr = measStatus.split("\\|");
        System.out.println("measStatusArr size: " + measStatusArr.length);
        for (String measStatusStr : measStatusArr) {
            System.out.println("measStatusStr: " + measStatusStr);
        }
        // String content =
        // "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
        // + "<html>"
        // + "<head>"
        // +
        // "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"
        // + "<title>蚁巡5月28日监控日报</title>"
        // + "<style>body{background: #fff}"
        // + "a:link {color:#046380;text-decoration:underline;}"
        // + "a:visited {color:#A7A37E;text-decoration:none;}"
        // + "a:hover {color:#002F2F;text-decoration:underline;}"
        // + "a:active {color:#046380;text-decoration:none;}"
        // + "table.table {border-collapse:collapse;}"
        // +
        // "table.table td,table.table th{border: 1px solid #ccc;border-collapse:collapse; padding: 5px;}"
        // + "table.table th{background: #efefef; text-align:left;}"
        // +
        // "p,.common{font-family:'微软雅黑', arial,  helvetica, sans-serif;font-size: 12px;color: #666666; font-weight:normal;}"
        // +
        // "ul{margin: 20px 0 0;padding: 0 0 0 21px;}.fb{font-weight:bold;}.f14{font-size:14px;}.f16{font-size: 16px;}.f25{font-size: 25px;}.lh18{line-height:18px;}.lh25{line-height:25px;}.gray{color: #999;}.mt30{margin-top:30px;}.bd{border: #666666 1px solid;}.bdb{border-bottom: #666666 1px solid;}.bg-gray{background: #efefef}.indent24{text-indent: 24px;}"
        // + "</style></head><body>"
        // +
        // "<table align=\"center\" width=\"100%\" cellpadding=\"2\" cellspacing=\"2\">"
        // +
        // "<tr><td class=\"common gray\">本邮件是蚁巡自动发送的监控日报，你可以通过本邮件快速了解昨日您所监控的设备概况。"
        // + "</td></tr></table>"
        // +
        // "<table class=\"mt30\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">"
        // +
        // "<tr><td><TABLE border=\"0\" align=\"center\"><TR><TD><p class=\"f25\">蚁巡5月28日监控日报</p></TD></TR></TABLE></td></tr>"
        // +
        // "<tr><td><TABLE class='mt30' width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
        // +
        // "<TR><TD><p class=\"fb f16\">设备统计</p><p class=\"indent24\">当前(5月28日12：00)，蚁巡共监控58台设备。其中网络设备xx台，Linux xx台，Windows xx台，待配置 xx台。共监控软件xx个。其中Solr4.x xx个，Redis xx个，网站xx个，进程xx个，待配置的软件xx个。</p>"
        // + "<p>待配置的设备为：Xx(机器名)(xxx.xxx.xxx.xxx、xxx.xxx.xxx.xxx(IP地址列表))。</p>"
        // +
        // "<p>待配置的软件为：Xx(机器名)(xxx.xxx.xxx.xxx、xxx.xxx.xxx.xxx(IP地址列表))上的xx(软件类型)。</p>"
        // + "</TD></TR></TABLE></td></tr><tr><td> "
        // +
        // "<TABLE class=\"mt30\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">"
        // +
        // "<TR><TD><p class=\"f16 fb\">设备状况</p><p>CPU利用<strong>Top10</strong>：</p>"
        // +
        // "<table class=\"table common\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
        // + "<tr><th>主机</th><th>IP</th><th>CPU利用率(均值)</th><th>环比增长</th></tr>"
        // +
        // "<tr><td>cassandra-user-110</td><td>172.19.104.110</td><td>70%</td><td>2%</td></tr></table><br />"
        // + "</TD></TR></TABLE></td></tr> "
        // +
        // "<tr><td><TABLE width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">"
        // +
        // "<TR><TD><p class=\"lh18\">如您不需要本邮件，请联系蚁巡的管理员。<br />是否希望了解蚁巡的新功能和新动态？请者关注我们的微博: <a href=\"http://e.weibo.com/antpatrol\">http://e.weibo.com/antpatrol</a>(新浪微博)。<br />"
        // +
        // "祝您使用愉快！<br />蚁坊软件 <a href=\"www.antvision.cn\" target=\"_blank\">www.antvision.cn</a></p>"
        // + "<br /><br /></TD></TR></TABLE></td></tr>" +
        // "</table></body></html>";
        // System.out.println(content);
    }
}
