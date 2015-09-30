package com.offcial;

public class SendXx {
    private String touser;
    private String msgtype;
    // 多条图文消息信息，默认第一个item为大图
    private Articles news;

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

    public Articles getNews() {
        return news;
    }

    public void setNews(Articles news) {
        this.news = news;
    }
}
