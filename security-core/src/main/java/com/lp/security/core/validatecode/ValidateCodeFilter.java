package com.lp.security.core.validatecode;

import com.lp.security.core.properties.SecurityProperties;
import com.lp.security.core.validatecode.ValidateCodeProcessor;
import com.lp.security.core.validatecode.image.ImageCode;
import com.lp.security.core.validatecode.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LIPENGAK
 * @Description: 图形验证码的验证过滤器
 * @date 2018-05-06 16:44
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{


    private AuthenticationFailureHandler authenticationFailureHandler;


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private SecurityProperties securityProperties;

    private Set<String> urlSet = new HashSet<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public Set<String> getUrlSet() {
        return urlSet;
    }

    public void setUrlSet(Set<String> urlSet) {
        this.urlSet = urlSet;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

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

        boolean action = false;
        for (String url:urlSet){
            if(antPathMatcher.match(url,request.getRequestURI())){
                action = true;
                break;
            }
        }

        if(action){
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
        ImageCode imageCodeSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
        String codeInRequest = request.getParameter("imageCode");

        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空！");
        }

        if(imageCodeSession == null){
            throw new ValidateCodeException("验证码不存在！");
        }

        if(imageCodeSession.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
            throw new ValidateCodeException("验证码已过期！");
        }

        if(!StringUtils.equalsIgnoreCase(codeInRequest,imageCodeSession.getCode())){
            throw new ValidateCodeException("验证码不匹配！");
        }
        sessionStrategy.removeAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),",");
        for (String url:urls){
            urlSet.add(url);
        }
        urlSet.add("/authentication/form");
    }
}
