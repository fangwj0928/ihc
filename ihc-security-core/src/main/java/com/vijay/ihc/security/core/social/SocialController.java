package com.vijay.ihc.security.core.social;

import com.vijay.ihc.security.core.social.support.SocialUserInfo;
import org.springframework.social.connect.Connection;

public abstract class SocialController {

    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }
}
