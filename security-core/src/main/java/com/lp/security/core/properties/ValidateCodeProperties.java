package com.lp.security.core.properties;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-07 21:46
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodePropreties sms = new SmsCodePropreties();

    public SmsCodePropreties getSms() {
        return sms;
    }

    public void setSms(SmsCodePropreties sms) {
        this.sms = sms;
    }

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
