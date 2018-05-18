package com.lp.security.core.validatecode.sms;

import com.lp.security.core.validatecode.AbstractValidateCodeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-17 22:59
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SmsCodeSender smsCodeSender;
    /**
     * 发送验证码，有子类实现
     *
     * @param request
     * @param validateCode
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) {
        String mobile = request.getParameter("mobile");
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
