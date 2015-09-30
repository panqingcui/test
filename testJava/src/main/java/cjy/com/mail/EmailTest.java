/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package cjy.com.mail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年9月12日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class EmailTest {
    @Test
    public static void testSendSimpleEmail() {
        // 用户名和对应的邮箱
        Map<String, String> nameAndAddrMap = new HashMap<String, String>();// 新建一个map
        nameAndAddrMap.put("张三", "panqingcui@163.com");
        String htmlContent = "<img src='D:\\a.png'>";// 邮件内容
        String subject = "新年好！";// 主题或者标题
        try {
            JavaMailSender.sendEmail(nameAndAddrMap, subject, htmlContent);// 测试发送邮件功能
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("邮件发送失败！");
            // logger.setMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        testSendSimpleEmail();
    }
}
