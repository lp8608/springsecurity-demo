package com.lp.security.core;

/**
 * @author LIPENGAK
 * @Description: 简单响应数据封装
 * @date 2018-05-06 15:05
 */
public class SimpleResponse {

    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
