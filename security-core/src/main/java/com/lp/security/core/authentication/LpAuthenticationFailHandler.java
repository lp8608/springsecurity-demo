package com.lp.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.security.core.properties.SecurityProperties;
import com.lp.security.core.util.LoginResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 认证失败处理
 * @date 2018-05-07 00:09
 */
@Component
public class LpAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;   //转JSON格式字符串

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
        }else{
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
