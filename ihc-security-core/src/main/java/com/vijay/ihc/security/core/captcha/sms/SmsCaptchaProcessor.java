package com.vijay.ihc.security.core.captcha.sms;

import com.vijay.ihc.security.core.captcha.Captcha;
import com.vijay.ihc.security.core.captcha.impl.AbstractCaptchaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 */
@Component
public class SmsCaptchaProcessor extends AbstractCaptchaProcessor<Captcha> {

    @Autowired
    private SmsCaptchaSender smsCaptchaSender;

    @Override
    protected void send(ServletWebRequest request, Captcha captcha) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        smsCaptchaSender.send(mobile, captcha.getCode());
    }
}
