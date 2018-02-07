package com.vijay.ihc.security.core.properties;

import lombok.Data;

@Data
public class CaptchaProperties {

    private ImageCaptchaProperties image = new ImageCaptchaProperties();

    private SmsCaptchaProperties sms = new SmsCaptchaProperties();

}
