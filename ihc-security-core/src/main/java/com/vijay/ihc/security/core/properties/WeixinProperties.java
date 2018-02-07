package com.vijay.ihc.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.social.SocialProperties;

@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinProperties extends SocialProperties {

    private String providerId = "weixin";
}
