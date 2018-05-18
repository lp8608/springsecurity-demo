package com.lp.security.core.validatecode.sms;

/**
 * @author LIPENGAK
 * @Description: sms短信发送接口
 * @date 2018-05-07 23:08
 */
public  interface SmsCodeSender {

    void send(String mobile, String code);

}
