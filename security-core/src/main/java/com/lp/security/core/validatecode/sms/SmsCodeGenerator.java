package com.lp.security.core.validatecode.sms;

import com.lp.security.core.properties.SecurityProperties;
import com.lp.security.core.validatecode.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-07 23:18
 */
@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generator(ServletWebRequest request) throws IOException {
        HttpServletRequest servletRequest = request.getRequest();
        //判断请求级的配置，如果没有则取securityProperties里面的配置
        int expireIn = ServletRequestUtils.getIntParameter(servletRequest,"expireIn",securityProperties.getCode().getSms().getExpireIn());
        int length = ServletRequestUtils.getIntParameter(servletRequest,"length",securityProperties.getCode().getSms().getLength());
        String code = RandomStringUtils.randomNumeric(length);
        return new ValidateCode(code,expireIn);

    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

}
