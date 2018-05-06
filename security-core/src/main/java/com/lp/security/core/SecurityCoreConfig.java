package com.lp.security.core;

import com.lp.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LIPENGAK
 * @Description:  配置类,是的SecurityProperties配置封装类生效
 * @date 2018-05-06 15:15
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {


}
