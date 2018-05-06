package com.lp.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO: 类描述
 *
 * @author LIPENGAK
 * @date $date$ $time$
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("TimeInterceptor preHandle 当前执行的方法为：{}.{}()",((HandlerMethod)handler).getBean().getClass().getName() , ((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("start",System.currentTimeMillis());
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("TimeInterceptor postHandle 耗时：{}毫秒",System.currentTimeMillis() - Long.valueOf(request.getAttribute("start").toString()));
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("TimeInterceptor afterCompletion 耗时：{}毫秒",System.currentTimeMillis() - Long.valueOf(request.getAttribute("start").toString()));
        logger.info("TImeInterceptor exception is ",ex);
    }
}
