package com.lp.security.core.properties;

/**
 * @author LIPENGAK
 * @Description: 图形验证码配置属性
 * @date 2018-05-07 21:44
 */
public class ImageCodeProperties extends SmsCodePropreties {
    public ImageCodeProperties( ) {
        setLength(4);
    }

    private int width = 67;
    private int height = 23;





    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
