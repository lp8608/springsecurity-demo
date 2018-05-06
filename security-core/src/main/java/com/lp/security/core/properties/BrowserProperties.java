package com.lp.security.core.properties;

/**
 * @author LIPENGAK
 * @Description: browser配置封装类
 * @date 2018-05-06 15:13
 */
public class BrowserProperties {

    private String loginPage = "/login.html";

    private String loginType = "REDIRECT";

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
