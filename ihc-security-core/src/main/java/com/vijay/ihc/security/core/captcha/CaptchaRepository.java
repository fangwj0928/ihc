package com.vijay.ihc.security.core.captcha;

import com.vijay.ihc.security.core.captcha.type.CaptchaType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码操作接口
 */
public interface CaptchaRepository {

    /**
     * 保存验证码
     * @param request 请求对象
     * @param captcha 验证码
     * @param captchaType 验证码类型
     */
    void save(ServletWebRequest request, Captcha captcha, CaptchaType captchaType);
    /**
     * 获取验证码
     * @param request 请求对象
     * @param captchaType 验证码类型
     * @return 验证码
     */
    Captcha get(ServletWebRequest request, CaptchaType captchaType);
    /**
     * 移除验证码
     * @param request 请求对象
     * @param captchaType 验证码类型
     */
    void remove(ServletWebRequest request, CaptchaType captchaType);

}
