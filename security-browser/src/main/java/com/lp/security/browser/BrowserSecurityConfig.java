package com.lp.security.browser;

import com.lp.security.core.SimpleResponse;
import com.lp.security.core.authentication.LpAuthenticationFailHandler;
import com.lp.security.core.authentication.LpAuthenticationSuccessHandler;
import com.lp.security.core.properties.SecurityProperties;
import com.lp.security.core.util.LoginResponseType;
import com.lp.security.core.validate.ValidateCodeFilter;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-05 20:45
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private LpAuthenticationFailHandler lpAuthenticationFailHandler;
    @Autowired
    private LpAuthenticationSuccessHandler lpAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(lpAuthenticationFailHandler);
        //默认basic登陆
        //http.httpBasic()
        //默认页面登陆
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(lpAuthenticationSuccessHandler)
                .failureHandler(lpAuthenticationFailHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)

        ;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
