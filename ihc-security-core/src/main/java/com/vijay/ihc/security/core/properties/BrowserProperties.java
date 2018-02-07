package com.vijay.ihc.security.core.properties;

import com.vijay.ihc.security.core.constant.SecurityConstants;
import lombok.Data;

@Data
public class BrowserProperties {

    private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;

    private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

    private String signOutUrl;

    private LoginResponseType loginType = LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;

    private SessionProperties session = new SessionProperties();
}
