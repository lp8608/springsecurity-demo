package com.lp.validatecode;

import com.lp.security.core.validatecode.image.ImageCode;
import com.lp.security.core.validatecode.ValidateCodeGenerator;
import com.lp.security.core.validatecode.sms.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-07 22:57
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public ValidateCode generator(ServletWebRequest request) throws IOException {
        logger.info("demo 应用中更高级的图形验证码生成器！");
        return null;
    }
}
