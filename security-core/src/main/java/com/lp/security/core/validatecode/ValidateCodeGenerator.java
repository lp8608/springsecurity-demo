package com.lp.security.core.validatecode;

import com.lp.security.core.validatecode.sms.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 验证码生成接口
 * @date 2018-05-07 22:46
 */
public interface ValidateCodeGenerator {

    ValidateCode generator(ServletWebRequest request) throws IOException;


}
