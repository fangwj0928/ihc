package com.vijay.ihc.security.core.properties;

import lombok.Data;

@Data
public class SmsCaptchaProperties {

    private int length = 4;

    private int expireIn = 60;

    private String url;

}
