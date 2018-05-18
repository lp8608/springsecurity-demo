package com.lp.security.core.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author LIPENGAK
 * @Description: validate code 验证码处理流程封装，不同的种类的验证码有不同的验证逻辑
 * @date 2018-05-17 22:23
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码在session中的名称前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     * @param request
     */
    void create(ServletWebRequest request) throws Exception;

}
