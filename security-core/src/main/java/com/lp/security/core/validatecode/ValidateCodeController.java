package com.lp.security.core.validatecode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author LIPENGAK
 * @Description: validate code 控制器
 * @date 2018-05-17 22:23
 */

@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessorMap;



    @GetMapping("/code/{type}")
    public void createcode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessorMap.get(type + "CodeProcessor").create(new ServletWebRequest(request,response));
    }


}
