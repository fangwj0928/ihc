package com.vijay.ihc.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ihc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private CaptchaProperties captcha = new CaptchaProperties();

    private SocialProperties social = new SocialProperties();

    private OAuth2Properties oauth2 = new OAuth2Properties();

}
