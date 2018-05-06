package com.lp.web.controller;

import com.lp.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author LIPENGAK
 * @date $date$ $time$
 */
@ControllerAdvice
public class ControllerExcpeitonHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handlerUserNotException(UserNotExistException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);
        result.put("id",ex.getId());
        result.put("message" , ex.getMessage());
        return result;
    }
}
