package com.vijay.ihc.security.core.properties;

import lombok.Data;

@Data
public class OAuth2Properties {

    private String jwtSigninKey = "ihc";

    private OAuth2ClientProperties[] clients = {};
}
