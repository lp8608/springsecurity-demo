package com.lp.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-06 16:49
 */
public class ValidateCodeException extends AuthenticationException {

    /**
     * Constructs a new instance of AuthenticationException using the
     * explanation supplied. All other fields default to null.
     *
     * @param explanation A possibly null string containing
     *                    additional detail about this exception.
     * @see Throwable#getMessage
     */
    public ValidateCodeException(String explanation) {
        super(explanation);
    }
}
