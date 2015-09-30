/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package cjy.com.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>发送内嵌图片,附件邮件。<p>
 * 
 * 创建日期 2015年9月12日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class JavaSendMail {
    // 记日志
    private static final Log EMAIL_LOG = LogFactory.getLog("EMAIL");
    private final static Pattern PATTERN;// 用于效验邮箱地址的正确性
    private String smtp = ""; // 邮件服务器主机名
    private String protocol = ""; // 邮件传输协议
    private String username = ""; // 登录用户名
    private String password = ""; // 登录密码
    private String to = ""; // 收件人地址
    private String subject = ""; // 邮件主题
    private String body = ""; // 邮件内容
    // 一个有规则的map，用作嵌入图片
    Map<String, String> map;
    // 存放附件
    List<String> list;
    private String sender = "蚁巡";// 发件人名字
    private String senderAddress = "panqingcui@antro.com";// 发件人的E-Mail地址
    static {
        PATTERN = Pattern.compile(".+@[^.@]+(\\.[^.@]+)+$");// 此处放弃了传统匹配方式，这是为了兼容非英文域名的电子邮箱
    }

    public JavaSendMail(Map<String, String> map, List<String> filelist, Map<String, String> image) {
        this(map.get("from"));
        this.smtp = map.get("smtp");
        this.protocol = map.get("protocol");
        this.username = map.get("username");
        this.password = map.get("password");
        this.to = map.get("to");
        this.subject = map.get("subject");
        this.body = map.get("body");
        this.list = filelist;
        this.map = image;
    }

    /**
     * 获取发送邮件的名称,邮件地址
     * @param from
     */
    private JavaSendMail(String from) {
        if (from == null) {
            throw new IllegalArgumentException("参数from不能为null。");
        }
        int leftSign = (from = from.trim()).charAt(from.length() - 1) == '>' ? from.lastIndexOf('<') : -1;
        senderAddress = leftSign > -1 ? from.substring(leftSign + 1, from.length() - 1).trim() : from;
        if (!PATTERN.matcher(senderAddress).find()) {
            throw new IllegalArgumentException("参数from不正确。");
        }
        sender = leftSign > -1 ? from.substring(0, leftSign).trim() : null;
        if (sender != null) {
            if (sender.length() == 0) {
                sender = null;
            } else if (sender.charAt(0) == '"' && sender.charAt(sender.length() - 1) == '"') {
                sender = sender.substring(1, sender.length() - 1).trim();
            }
        }
    }

    /**
     * 发送邮件
     * @throws Exception
     */
    public boolean send() throws Exception {
        Transport ts = null;
        try {
            Properties pros = new Properties();
            pros.setProperty("mail.transport.protocol", this.protocol);
            pros.setProperty("mail.host", this.smtp);
            pros.put("mail.smtp.auth", "true");
            Authenticator ma = new MySendMailAuthenticator(this.username, this.password);
            Session session = Session.getInstance(pros, ma);
            session.setDebug(false);
            MimeMessage msg = createMessage(session);
            ts = session.getTransport();
            ts.connect();
            ts.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
        } finally {
            if (ts != null) {
                ts.close();
            }
        }
        return true;
    }

    public MimeMessage createMessage(Session session) throws Exception {
        MimeMessage message = new MimeMessage(session);
        String nick = "";
        nick = javax.mail.internet.MimeUtility.encodeText("蚁巡");
        message.setFrom(new InternetAddress(nick + "<" + this.senderAddress + ">"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(this.subject);
        MimeMultipart allMultipart = new MimeMultipart();
        // 创建代表邮件正文和附件的各个MimeBodyPart对象
        MimeBodyPart contentpart = createContent(this.body);
        allMultipart.addBodyPart(contentpart);
        // 创建用于组合邮件正文和附件的MimeMultipart对象
        for (int i = 0; i < list.size(); i++) {
            allMultipart.addBodyPart(createAttachment(list.get(i)));
        }
        // 设置整个邮件内容为最终组合出的MimeMultipart对象
        message.setContent(allMultipart);
        message.saveChanges();
        return message;
    }

    /**
     * 内容
     * @param body
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked" })
    public MimeBodyPart createContent(String body) throws Exception {
        // 创建代表组合Mime消息的MimeMultipart对象，将该MimeMultipart对象保存到MimeBodyPart对象
        MimeBodyPart contentPart = new MimeBodyPart();
        MimeMultipart contentMultipart = new MimeMultipart("related");
        // 创建用于保存HTML正文的MimeBodyPart对象，并将它保存到MimeMultipart中
        MimeBodyPart htmlbodypart = new MimeBodyPart();
        htmlbodypart.setContent(this.body, "text/html;charset=UTF-8");
        contentMultipart.addBodyPart(htmlbodypart);
        if (map != null && map.size() > 0) {
            Set<Entry<String, String>> set = map.entrySet();
            for (Iterator iterator = set.iterator(); iterator.hasNext();) {
                Entry<String, String> entry = (Entry<String, String>) iterator.next();
                // 创建用于保存图片的MimeBodyPart对象，并将它保存到MimeMultipart中
                MimeBodyPart gifBodyPart = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(entry.getValue());// 图片所在的目录的绝对路径
                gifBodyPart.setDataHandler(new DataHandler(fds));
                gifBodyPart.setContentID(entry.getKey()); // cid的值
                contentMultipart.addBodyPart(gifBodyPart);
            }
        }
        // 将MimeMultipart对象保存到MimeBodyPart对象
        contentPart.setContent(contentMultipart);
        return contentPart;
    }

    /**
     * 添加附件
     * @param filename
     * @return
     * @throws Exception
     */
    public MimeBodyPart createAttachment(String filename) throws Exception {
        // 创建保存附件的MimeBodyPart对象，并加入附件内容和相应的信息
        MimeBodyPart attachPart = new MimeBodyPart();
        FileDataSource fsd = new FileDataSource(filename);
        attachPart.setDataHandler(new DataHandler(fsd));
        attachPart.setFileName(fsd.getName());
        return attachPart;
    }

    // 向邮件服务器提交认证信息
    class MySendMailAuthenticator extends Authenticator {
        String username = "";
        String password = "";

        public MySendMailAuthenticator(String user, String pass) {
            this.username = user;
            this.password = pass;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    public static void main(String[] args) throws Exception {
        String content = "<body><table border='1' align='center' cellpadding='0' cellspacing='0'><tr><td>测试</td></tr></table></body>";
        Map<String, String> map = new HashMap<String, String>();
        // 邮件服务器主机名(smtp服务器地址)
        // 如：qq的smtp服务器地址：smtp.qq.com
        map.put("smtp", "smtp.exmail.qq.com");
        // 邮件传输协议：如smtp
        map.put("protocol", "smtp");
        // 登录邮箱的用户名
        map.put("username", "panqingcui@antrol.com");
        // 登录邮箱的密码
        map.put("password", "antrol890617");
        // 发送人的帐号
        map.put("from", "panqingcui@antrol.com");
        // 接收人的帐号，多个以","号隔开
        map.put("to", "panqingcui@163.com");
        // 邮件主题
        map.put("subject", "这个邮件有点牛逼");
        // 邮件内容
        map.put("body", content);
        // 内嵌了多少张图片，如果没有，则new一个不带值的Map
        Map<String, String> image = new HashMap<String, String>();
        // 附件的绝对路径,不发附件则new一个不带值的List
        List<String> list = new ArrayList<String>();
        // 创建实例
        JavaSendMail sm = new JavaSendMail(map, list, image);
        // 执行发送
        sm.send();
    }
}
