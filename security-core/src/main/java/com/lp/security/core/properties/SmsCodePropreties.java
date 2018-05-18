package com.lp.security.core.properties;

/**
 * @author LIPENGAK
 * @Description: 短信验证码配置属性
 * @date 2018-05-07 23:23
 */
public class SmsCodePropreties {
    private int length = 4;
    private int expireIn = 60;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
