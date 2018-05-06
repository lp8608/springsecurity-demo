package com.lp.web.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * TODO: 类描述
 *
 * @author LIPENGAK
 * @date $date$ $time$
 */
//@Component
public class TimeFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(TimeFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("run TimeFilter init");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("TimeFilter start");
        long start = System.currentTimeMillis();
        chain.doFilter(request,response);
        logger.info("TimeFiler 总耗时:{}毫秒" , System.currentTimeMillis() - start);
        logger.info("TimeFilter finish");

    }

    @Override
    public void destroy() {
        System.out.println("run TimeFilter destroy");
    }
}
