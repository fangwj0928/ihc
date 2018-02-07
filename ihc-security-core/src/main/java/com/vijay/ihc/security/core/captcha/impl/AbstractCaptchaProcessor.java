package com.vijay.ihc.security.core.captcha.impl;

import com.vijay.ihc.security.core.captcha.Captcha;
import com.vijay.ihc.security.core.captcha.CaptchaException;
import com.vijay.ihc.security.core.captcha.CaptchaGenerator;
import com.vijay.ihc.security.core.captcha.CaptchaProcessor;
import com.vijay.ihc.security.core.captcha.CaptchaRepository;
import com.vijay.ihc.security.core.captcha.type.CaptchaType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 验证码处理器抽象类
 * @param <T> 验证码对象类型
 */
public abstract class AbstractCaptchaProcessor<T extends Captcha> implements CaptchaProcessor {

    @Autowired
    private Map<String, CaptchaGenerator> captchaGenerators;

    @Autowired
    private CaptchaRepository captchaRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        T captcha = generate(request);
        save(request, captcha);
        send(request, captcha);
    }

    @Override
    public void validate(ServletWebRequest request) {

        CaptchaType codeType = getCaptchaType(request);

        Captcha codeInSession = captchaRepository.get(request, codeType);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new CaptchaException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new CaptchaException(codeType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new CaptchaException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            captchaRepository.remove(request, codeType);
            throw new CaptchaException(codeType + "验证码已过期");
        }

        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new CaptchaException(codeType + "验证码不匹配");
        }

        captchaRepository.remove(request, codeType);
    }

    protected abstract void send(ServletWebRequest request, T captcha) throws Exception;

    private void save(ServletWebRequest request, T captcha) {
        Captcha code = new Captcha(captcha.getCode(), captcha.getExpireTime());
        captchaRepository.save(request, code, getCaptchaType(request));
    }

    /**
     * 生成验证码对象
     * @param request 请求响应对象
     * @return 验证码对象
     */
    private T generate(ServletWebRequest request) {
        String type = getCaptchaType(request).toString().toLowerCase();
        String generatorName = type + CaptchaGenerator.class.getSimpleName();
        CaptchaGenerator captchaGenerator = captchaGenerators.get(generatorName);
        if (captchaGenerator == null) {
            throw new CaptchaException("验证码生成器" + generatorName + "不存在");
        }
        return (T) captchaGenerator.generate(request);
    }

    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request 请求对象
     * @return 验证码类型
     */
    private CaptchaType getCaptchaType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CaptchaProcessor");
        //String type = getProcessorType(request);
        return CaptchaType.valueOf(type.toUpperCase());
    }

    /**
     * 根据请求的url获取校验码的类型
     * @param request 请求对象
     * @return 请求验证码类型字符串
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/captcha/");
    }



}
