package com.lp.security.core.validatecode.image;

import com.lp.security.core.validatecode.AbstractValidateCodeProcessor;
import com.lp.security.core.validatecode.sms.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-17 23:00
 */
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 发送验证码，有子类实现
     *
     * @param request
     * @param validateCode
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws IOException {
        ImageCode imageCode = (ImageCode) validateCode;
        logger.info("发送图形验证码：{}" , validateCode.getCode());
        ImageIO.write(imageCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }


}
