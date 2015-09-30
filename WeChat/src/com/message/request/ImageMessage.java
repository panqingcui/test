package com.message.request;

/**
 * 
 * <p>图片消息。<p>
 * 
 * 创建日期 2014年4月17日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class ImageMessage extends RequestBaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
