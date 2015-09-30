import java.util.Date;

/***
 * CommonSms 短信用于全局变量
 */
public class CommonSms {
    /** id */
    private int id;
    /** 短信内容 */
    private String smstext;
    /** 短信发送方 */
    private String sender;// 短信发送方
    /** 短信接收发 */
    private String recver;// 短信接收发
    /** 时间 */
    private Date date;

    public String getSmstext() {
        return smstext;
    }

    public void setSmstext(String smstext) {
        this.smstext = smstext;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecver() {
        return recver;
    }

    public void setRecver(String recver) {
        this.recver = recver;
    }
}
