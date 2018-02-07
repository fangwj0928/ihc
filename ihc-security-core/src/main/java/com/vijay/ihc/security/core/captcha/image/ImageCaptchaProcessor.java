package com.vijay.ihc.security.core.captcha.image;

import com.vijay.ihc.security.core.captcha.impl.AbstractCaptchaProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 图形验证码处理器
 */
@Component
public class ImageCaptchaProcessor extends AbstractCaptchaProcessor<ImageCaptcha> {

    @Override
    protected void send(ServletWebRequest request, ImageCaptcha captcha) throws Exception {
        ImageIO.write(captcha.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
