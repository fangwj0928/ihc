package com.vijay.ihc.demo.code;

import com.vijay.ihc.security.core.captcha.CaptchaGenerator;
import com.vijay.ihc.security.core.captcha.image.ImageCaptcha;
import org.springframework.web.context.request.ServletWebRequest;

//@Component("imageCaptchaGenerator")
public class DemoImageCaptchaGenerator implements CaptchaGenerator {

    @Override
    public ImageCaptcha generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码");
        return null;
    }
}
