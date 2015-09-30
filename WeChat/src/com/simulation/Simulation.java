package com.simulation;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.offcial.MySSLProtocolSocketFactory;

public class Simulation {
    public final static String LOGIN_URL = "http://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
    public final static String INDEX_URL = "http://mp.weixin.qq.com/cgi-bin/indexpage?t=wxm-index&lang=zh_CN";
    public final static String SENDMSG_URL = "https://mp.weixin.qq.com/cgi-bin/singlesend";
    public final static String FANS_URL = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
    public final static String LOGOUT_URL = "http://mp.weixin.qq.com/cgi-bin/logout?t=wxm-logout&lang=zh_CN";
    public final static String DOWNLOAD_URL = "http://mp.weixin.qq.com/cgi-bin/downloadfile?";
    public final static String VERIFY_CODE = "http://mp.weixin.qq.com/cgi-bin/verifycode?";
    public final static String POST_MSG = "https://mp.weixin.qq.com/cgi-bin/masssend?t=ajax-response";
    public final static String VIEW_HEAD_IMG = "http://mp.weixin.qq.com/cgi-bin/viewheadimg";
    public final static String GET_IMG_DATA = "http://mp.weixin.qq.com/cgi-bin/getimgdata";
    public final static String GET_REGIONS = "http://mp.weixin.qq.com/cgi-bin/getregions";
    public final static String GET_MESSAGE = "http://mp.weixin.qq.com/cgi-bin/getmessage";
    public final static String OPER_ADVANCED_FUNC = "http://mp.weixin.qq.com/cgi-bin/operadvancedfunc";
    public final static String MASSSEND_PAGE = "http://mp.weixin.qq.com/cgi-bin/masssendpage";
    public final static String FILE_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/filemanagepage";
    public final static String OPERATE_APPMSG = "https://mp.weixin.qq.com/cgi-bin/operate_appmsg?token=416919388&lang=zh_CN&sub=edit&t=wxm-appmsgs-edit-new&type=10&subtype=3&ismul=1";
    public final static String FMS_TRANSPORT = "http://mp.weixin.qq.com/cgi-bin/fmstransport";
    // public final static String CONTACT_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage";
    public final static String CONTACT_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/contactmanage";
    public final static String OPER_SELF_MENU = "http://mp.weixin.qq.com/cgi-bin/operselfmenu";
    public final static String REPLY_RULE_PAGE = "http://mp.weixin.qq.com/cgi-bin/replyrulepage";
    public final static String SINGLE_MSG_PAGE = "http://mp.weixin.qq.com/cgi-bin/singlemsgpage";
    public final static String USER_INFO_PAGE = "http://mp.weixin.qq.com/cgi-bin/userinfopage";
    public final static String DEV_APPLY = "http://mp.weixin.qq.com/cgi-bin/devapply";
    public final static String UPLOAD_MATERIAL = "https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&type=2&token=416919388&t=iframe-uploadfile&lang=zh_CN&formId=1";
    public final static String USER_AGENT_H = "User-Agent";
    public final static String REFERER_H = "Referer";
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
    public final static String UTF_8 = "UTF-8";
    private HttpClient client = new HttpClient();
    private Cookie[] cookies;
    private String cookiestr;
    private String token;
    private int loginErrCode;
    private String loginErrMsg;
    private int msgSendCode;
    private String msgSendMsg;
    private List<Fan> fans;
    private String loginUser;
    private String loginPwd;
    public boolean isLogin = false;
    public String slave_user;
    public String slave_sid;
    public String sig;

    public Simulation(String user, String pwd) {
        this.loginUser = user;
        this.loginPwd = pwd;
    }

    /**
     * 模拟登陆
     * @throws HttpException
     * @throws IOException
     */
    private boolean _login() throws HttpException, IOException {
        String url = "https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN&f=json&pwd="
                + DigestUtils.md5Hex(this.loginPwd.getBytes()) + "&username=" + this.loginUser;
        try {
            HttpClient client = new HttpClient();
            Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
            Protocol.registerProtocol("https", myhttps);
            PostMethod getMethod = new PostMethod(url);
            getMethod.setRequestHeader("Referer", "https://mp.weixin.qq.com/");
            getMethod.setRequestHeader("Host", "mp.weixin.qq.com");
            client.executeMethod(getMethod);
            Cookie[] cookies = client.getState().getCookies();
            String returnStr = getMethod.getResponseBodyAsString();
            token = returnStr.split("token=")[1].split("\"")[0];
            System.out.println("token:" + token);
            StringBuffer cookie = new StringBuffer();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("slave_user")) {
                    slave_user = cookies[i].getValue();
                } else if (cookies[i].getName().equals("slave_sid")) {
                    slave_sid = cookies[i].getValue();
                }
                cookie.append(cookies[i].getName()).append("=").append(cookies[i].getValue()).append(";");
            }
            this.cookiestr = cookie.toString();
            this.isLogin = true;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 从登录成功的信息中分离出token信息
     * 
     * @param s
     * @return
     */
    private String getToken(String s) {
        try {
            if (StringUtils.isBlank(s))
                return null;
            String[] ss = StringUtils.split(s, "?");
            String[] params = null;
            if (ss.length == 2) {
                if (!StringUtils.isBlank(ss[1]))
                    params = StringUtils.split(ss[1], "&");
            } else if (ss.length == 1) {
                if (!StringUtils.isBlank(ss[0]) && ss[0].indexOf("&") != -1)
                    params = StringUtils.split(ss[0], "&");
            } else {
                return null;
            }
            for (String param : params) {
                if (StringUtils.isBlank(param))
                    continue;
                String[] p = StringUtils.split(param, "=");
                if (null != p && p.length == 2 && StringUtils.equalsIgnoreCase(p[0], "token"))
                    System.out.println(p[1]);
                return p[1];
            }
        } catch (Exception e) {
            String info = "【解析Token失败】【发生异常：" + e.getMessage() + "】";
            System.err.println(info);
            // log.debug(info);
            // log.info(info);
            return null;
        }
        return null;
    }

    /**
     * 获取粉丝列表，返回粉丝数量，出错则返回-1
     * @author trprebel
     * @return
     */
    public List<Fan> getFans() {
        try {
            String paramStr = "?t=user/index&token=" + this.token + "&lang=zh_CN&pagesize=1000&pageidx=0&type=0";
            // String paramStr = "?t=user/index&pagesize=10&pageidx=0&type=4";
            // if (!this.isLogin) {
            // this._login();
            // }
            GetMethod get = new GetMethod(CONTACT_MANAGE_PAGE + paramStr);
            get.setRequestHeader(REFERER_H, INDEX_URL);
            get.setRequestHeader("Cookie", this.cookiestr);
            int status = client.executeMethod(get);
            if (status == HttpStatus.SC_OK) {
                return parseFans(get.getResponseBodyAsString());
            }
            return null;
        } catch (Exception e) {
            String info = "【获取粉丝数失败】【可能登录过期】";
            System.err.println(info);
            // log.debug(info);
            // log.info(info);
            return null;
        }
    }

    /**
     * 从返回文本中提取粉丝数量
     * 
     * @param text
     * @return
     */
    private int parseFansCount(String text) {
        try {
            StringBuffer json = new StringBuffer();
            final String start = "DATA.groupList =";
            for (int i = text.indexOf(start) + start.length(), len = text.length(); i < len; i++) {
                char ci = text.charAt(i);
                if (ci == ';') {
                    break;
                }
                json.append(text.charAt(i));
            }
            String txt = json.toString().replaceAll("[*]1", "").replaceAll("defaultGroupName\\[0\\] \\|\\|", "")
                    .replaceAll("defaultGroupName\\[1\\] \\|\\|", "").replaceAll("defaultGroupName\\[2\\] \\|\\|", "")
                    .replaceAll("defaultGroupName\\[100\\] \\|\\|", "");
            List<FansCount> fans = JSON.parseArray(txt, FansCount.class);
            if (null != fans && !fans.isEmpty())
                for (FansCount fan : fans)
                    if (fan.getId() == 0)
                        return fan.getNum();
        } catch (Exception e) {
            String info = "【解析粉丝数失败】 " + "\t\n【文本：】\t\n" + text + "\t\n" + "【发生异常：" + e.getMessage() + "】";
            System.err.println(info);
            // log.debug(info);
            // log.info(info);
            return -1;
        }
        return -1;
    }

    /**
     * 解析粉丝列表，将粉丝列表存入List<fan>
     * @param text
     * @return
     */
    private List<Fan> parseFans(String text) {
        try {
            int liststart = text.indexOf("cgiData") + 8;
            int listend = text.indexOf("};", liststart) + 1;
            text = text.substring(liststart, listend);
            int friendliststart = text.indexOf("contacts") + 10;
            int friendlistend = text.indexOf("contacts", friendliststart) - 3;
            String friendlistjson = text.substring(friendliststart, friendlistend);
            fans = JSON.parseArray(friendlistjson, Fan.class);
            System.out.println("粉丝列表：");
            /**
             * id:就是后面要用到的fakeid nick_name:昵称 remark_name:备注 group_id:0为未分组,
             */
            for (Fan fan : fans) {
                System.out.println("ID:" + fan.getId() + " nick_name:" + fan.getNick_name() + " remark_name:"
                        + fan.getRemark_name() + " group_id:" + fan.getGroup_id());
            }
            return fans;
        } catch (Exception e) {
            String info = "【解析粉丝数失败】 " + "\t\n【文本：】\t\n" + text + "\t\n" + "【发生异常：" + e.getMessage() + "】";
            System.err.println(info);
            // log.debug(info);
            // log.info(info);
            return null;
        }
    }

    public boolean sendMessageById(String str, String wxId) {
        try {
            String url = "http://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response&lang=zh_CN&ajax=1&content="
                    + URLEncoder.encode(str, "UTF-8") + "&error=false&tofakeid=" + wxId + "&type=1&token=" + token;
            HttpClient client = new HttpClient();
            PostMethod getMethod = new PostMethod(url);
            getMethod.setRequestHeader("Cookie", "slave_user=" + slave_user + ";slave_sid=" + slave_sid);
            getMethod.setRequestHeader("Referer", "http://mp.weixin.qq.com/cgi-bin/singlemsgpage?fromfakeid=" + wxId
                    + "&msgid=&source=&count=20&t=wxm-singlechat&lang=zh_CN");
            client.executeMethod(getMethod);
            String returnStr = getMethod.getResponseBodyAsString();
            System.out.println(returnStr);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String args[]) {
        Simulation login = new Simulation(FanConstants.USERNAME, FanConstants.PWD);
        // "ret":10706,"err_msg":"customer block"
        // {"base_resp":{"ret":10706,"err_msg":"customer block"}}
        try {
            // 登陆成功后获取关注列表
            if (login._login()) {
                List<Fan> fans = login.getFans();
                for (Fan fan : fans) {
                    login.sendMessageById("欢迎加入翱翔家族！", fan.getId());
                }
            }
            // login.sendMessageById("hellofff", fans.get(0).getId());
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
