package com.vijay.ihc.security.app.captcha.impl;


import com.vijay.ihc.security.core.captcha.Captcha;
import com.vijay.ihc.security.core.captcha.CaptchaException;
import com.vijay.ihc.security.core.captcha.CaptchaRepository;
import com.vijay.ihc.security.core.captcha.type.CaptchaType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCaptchaRepository implements CaptchaRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, Captcha captcha, CaptchaType captchaType) {

        redisTemplate.opsForValue().set(buildKey(request, captchaType), captcha, 30, TimeUnit.MINUTES);
    }

    @Override
    public Captcha get(ServletWebRequest request, CaptchaType captchaType) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, captchaType));
        if (value == null) {
            return null;
        }
        return (Captcha) value;
    }

    @Override
    public void remove(ServletWebRequest request, CaptchaType captchaType) {
        redisTemplate.delete(buildKey(request, captchaType));
    }

    private String buildKey(ServletWebRequest request, CaptchaType type) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new CaptchaException("请在请求头中携带deviceId参数");
        }
        return "captcha:" + type.toString().toLowerCase() + ":" + deviceId;
    }
}
