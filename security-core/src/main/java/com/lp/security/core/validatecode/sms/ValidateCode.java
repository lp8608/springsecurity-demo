package com.lp.security.core.validatecode.sms;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-07 23:02
 */
public class ValidateCode {
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
    public boolean isExpried(){
        return LocalDateTime.now().isAfter(getExpireTime());
    }

}
