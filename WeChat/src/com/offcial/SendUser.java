package com.offcial;

/**
 * 
 * <p>封装向微信用户发送消息信息。<p>
 * 
 * 创建日期 2014年4月18日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class SendUser {
    private String touser;
    private String msgtype;
    private Content text;

    public Content getText() {
        return text;
    }

    public void setText(Content text) {
        this.text = text;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}
