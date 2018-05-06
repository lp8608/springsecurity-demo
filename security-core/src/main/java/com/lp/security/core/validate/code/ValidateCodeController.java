package com.lp.security.core.validate.code;

import com.lp.security.core.util.VerifyCodeUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-06 16:16
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = VerifyCodeUtils.createImageCode(4,50,67,23);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }



}
