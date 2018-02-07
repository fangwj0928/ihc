package com.vijay.ihc.security.core.captcha;

import com.vijay.ihc.security.core.captcha.type.CaptchaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证码处理器调用类
 */
@Component
public class CaptchaProcessorHolder {

    @Autowired
    private Map<String, CaptchaProcessor> captchaProcessors;

    /**
     * 根据验证码类型对象查询验证码处理器
     * @param type 验证码类型对象
     * @return
     */
    public CaptchaProcessor findCaptchaProcessor(CaptchaType type) {
        return findCaptchaProcessor(type.toString());
    }

    /**
     * 根据验证码类型字符串查询验证码处理器
     * @param type 验证码类型字符串
     * @return
     */
    public CaptchaProcessor findCaptchaProcessor(String type) {
        String name = type.toLowerCase() + CaptchaProcessor.class.getSimpleName();
        CaptchaProcessor processor = captchaProcessors.get(name);
        if (processor == null) {
            throw new CaptchaException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}
