package com.lp.security.core.validatecode;

import com.lp.security.core.validatecode.sms.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-17 22:31
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    /**
     * session 工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 收集系统中所有的{@link ValidateCodeGenerator} 接口的实现类
     */
    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGeneratorMap;

    /**
     * 创建验证码
     *
     * @param request
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        ValidateCode validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    private ValidateCode generate(ServletWebRequest request) throws Exception {
        String type = getProcessType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type + "CodeGenerator");
        if (validateCodeGenerator != null){
            return validateCodeGenerator.generator(request);
        }else{
            throw  new Exception("找不到名为：" + type + "CodeGenerator" + "的验证码生成器");
        }

    }
    /**
     * 发送验证码，有子类实现
     * @param request
     * @param validateCode
     */
    protected abstract void send(ServletWebRequest request,ValidateCode validateCode) throws IOException;

    /**
     * 保存验证码到session
     * @param request
     * @param validateCode
     */
    protected void save(ServletWebRequest request , ValidateCode validateCode){
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX + getProcessType(request).toUpperCase(),validateCode);
    }

    /**
     * 根据请求的url获取校验码的类型
     * @param request
     * @return
     */
    private String getProcessType(ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }
}
