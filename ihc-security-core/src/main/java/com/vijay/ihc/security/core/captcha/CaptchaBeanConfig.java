package com.vijay.ihc.security.core.captcha;

import com.vijay.ihc.security.core.captcha.image.ImageCaptchaGenerator;
import com.vijay.ihc.security.core.captcha.sms.DefaultSmsCaptchaSender;
import com.vijay.ihc.security.core.captcha.sms.SmsCaptchaSender;
import com.vijay.ihc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaptchaBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCaptchaGenerator")
    public CaptchaGenerator imageCaptchaGenerator() {
        ImageCaptchaGenerator captchaGenerator = new ImageCaptchaGenerator();
        captchaGenerator.setSecurityProperties(securityProperties);
        return captchaGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCaptchaSender.class)
    public SmsCaptchaSender defaultSmsCaptchaSender() {
        DefaultSmsCaptchaSender smsCaptchaSender = new DefaultSmsCaptchaSender();
        return smsCaptchaSender;
    }
}
