package com.vijay.ihc.security.core.social.weixin.connect;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.social.oauth2.AccessGrant;

@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinAccessGrant extends AccessGrant {

    private static final long serialVersionUID = -6855198154891304131L;

    private String openId;

    public WeixinAccessGrant() {
        super("");
    }

    public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

}
