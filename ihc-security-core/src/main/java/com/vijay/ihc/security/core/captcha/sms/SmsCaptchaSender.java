package com.vijay.ihc.security.core.captcha.sms;

/**
 * 短信发送接口
 */
public interface SmsCaptchaSender {

    void send(String mobile, String code);
}
