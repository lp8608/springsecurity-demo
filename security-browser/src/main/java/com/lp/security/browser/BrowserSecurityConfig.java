package com.lp.security.browser;

import com.lp.security.core.authentication.LpAuthenticationFailHandler;
import com.lp.security.core.authentication.LpAuthenticationSuccessHandler;
import com.lp.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.lp.security.core.authentication.mobile.SmsCodeFilter;
import com.lp.security.core.properties.SecurityProperties;
import com.lp.security.core.validatecode.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(lpAuthenticationFailHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();


        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(lpAuthenticationFailHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();
        //默认basic登陆
        //http.httpBasic()
        //默认页面登陆
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class).formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(lpAuthenticationSuccessHandler)
                .failureHandler(lpAuthenticationFailHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);

        ;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
