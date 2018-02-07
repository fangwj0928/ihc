package com.vijay.ihc.security.core.captcha;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器接口
 */
public interface CaptchaGenerator {

    Captcha generate(ServletWebRequest request);
}
