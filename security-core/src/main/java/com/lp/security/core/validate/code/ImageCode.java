package com.lp.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author LIPENGAK
 * @Description: 图形验证码实体类
 * @date 2018-05-06 16:11
 */
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expiredSeconds) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expiredSeconds);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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
        return LocalDateTime.now().isAfter(expireTime);
    }
}
