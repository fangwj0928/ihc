package com.vijay.ihc.security.core.properties;

import com.vijay.ihc.security.core.constant.SecurityConstants;
import lombok.Data;

@Data
public class SessionProperties {

    /**
     * 同一个用户在系统中的最大session数，默认为1
     */
    private int maximumSessions = 1;

    /**
     * 达到最大session时是否阻止新的登录请求，默认false，不阻止，新的登陆会将老的登陆失效掉
     */
    private  boolean maxSessionPreventsLogin;

    /**
     * session失效时跳转的地址
     */
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;
}
