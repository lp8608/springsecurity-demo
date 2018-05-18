package com.lp.security.core.validatecode.image;

import com.lp.security.core.properties.SecurityProperties;
import com.lp.security.core.util.VerifyCodeUtils;
import com.lp.security.core.validatecode.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 图片验证码生成器
 * @date 2018-05-07 22:48
 */
@Component
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generator(ServletWebRequest request) throws IOException {
        HttpServletRequest servletRequest = request.getRequest();
        //判断请求级的配置，如果没有则取securityProperties里面的配置
        int height = ServletRequestUtils.getIntParameter(servletRequest,"height",securityProperties.getCode().getImage().getHeight());
        int width = ServletRequestUtils.getIntParameter(servletRequest,"width",securityProperties.getCode().getImage().getWidth());
        int expireIn = ServletRequestUtils.getIntParameter(servletRequest,"expireIn",securityProperties.getCode().getImage().getExpireIn());
        int length = ServletRequestUtils.getIntParameter(servletRequest,"length",securityProperties.getCode().getImage().getLength());
        return  VerifyCodeUtils.createImageCode(length,expireIn,width,height);
    }


    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
