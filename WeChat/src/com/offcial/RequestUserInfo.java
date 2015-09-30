package com.offcial;

/**
 * 
 * <p>获取用户的信息。<p>
 * 
 * 创建日期 2014年4月18日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class RequestUserInfo {
    private String count;
    private OpenID data;
    private String next_openid;

    public String getCount() {
        return count;
    }

    public OpenID getData() {
        return data;
    }

    public void setData(OpenID data) {
        this.data = data;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }
}
