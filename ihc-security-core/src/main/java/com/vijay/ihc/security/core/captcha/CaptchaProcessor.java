package com.vijay.ihc.security.core.captcha;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器
 */
public interface CaptchaProcessor {

    /**
     * 创建校验码
     *
     * @param request 请求响应对象
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param request 请求响应对象
     * @throws Exception
     */
    void validate(ServletWebRequest request);
}
