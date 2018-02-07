package com.vijay.ihc.security.core.captcha;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {


    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaException(String msg) {
        super(msg);
    }
}
