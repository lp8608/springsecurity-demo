package com.lp.security.core.validatecode.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author LIPENGAK
 * @Description: 默认短信发送器
 * @date 2018-05-07 23:09
 */
@Component
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void send(String mobile, String code) {
        logger.info("向{}发送短信验证码：{}",mobile,code);
    }
}
