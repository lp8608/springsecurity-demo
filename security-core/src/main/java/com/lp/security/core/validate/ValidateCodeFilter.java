package com.lp.security.core.validate;

import com.lp.security.core.validate.code.ImageCode;
import com.lp.security.core.validate.code.ValidateCodeController;
import com.lp.security.core.validate.code.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-06 16:44
 */
public class ValidateCodeFilter extends OncePerRequestFilter{


    private AuthenticationFailureHandler authenticationFailureHandler;


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }


    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(StringUtils.equals("/authentication/form",request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(),"POST")){
            //验证图形验证码
            try{
                validateCode(new ServletWebRequest(request));
            }catch (ValidateCodeException ex){
                authenticationFailureHandler.onAuthenticationFailure(request,response,ex);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validateCode(ServletWebRequest request) throws ValidateCodeException{
        ImageCode imageCodeSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        String codeInRequest = request.getParameter("imageCode");

        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空！");
        }

        if(imageCodeSession == null){
            throw new ValidateCodeException("验证码不存在！");
        }

        if(imageCodeSession.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期！");
        }

        if(!StringUtils.equalsIgnoreCase(codeInRequest,imageCodeSession.getCode())){
            throw new ValidateCodeException("验证码不匹配！");
        }
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
    }
}
