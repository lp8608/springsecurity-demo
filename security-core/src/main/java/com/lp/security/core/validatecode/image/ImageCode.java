package com.lp.security.core.validatecode.image;

import com.lp.security.core.validatecode.sms.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author LIPENGAK
 * @Description: 图形验证码实体类
 * @date 2018-05-06 16:11
 */
public class ImageCode  extends ValidateCode {

    private BufferedImage image;



    public ImageCode(BufferedImage image, String code, int expiredSeconds) {
        super(code,LocalDateTime.now().plusSeconds(expiredSeconds));
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }



}
