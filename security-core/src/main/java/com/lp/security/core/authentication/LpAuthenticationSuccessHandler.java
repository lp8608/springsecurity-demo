package com.lp.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.security.core.properties.SecurityProperties;
import com.lp.security.core.util.LoginResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-07 00:13
 */
@Component
public class LpAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;   //转JSON格式字符串

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        log.info("登录成功");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
