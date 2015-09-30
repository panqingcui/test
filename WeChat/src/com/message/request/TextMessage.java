package com.message.request;

/**
 * 
 * <p>文本消息。<p>
 * 
 * 创建日期 2014年4月17日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TextMessage extends RequestBaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
