package com.vijay.ihc.security.core.captcha.sms;

import com.vijay.ihc.security.core.captcha.Captcha;
import com.vijay.ihc.security.core.captcha.CaptchaGenerator;
import com.vijay.ihc.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class SmsCaptchaGenerator implements CaptchaGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public Captcha generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCaptcha().getSms().getLength());
        return new Captcha(code, securityProperties.getCaptcha().getSms().getExpireIn());
    }
}
