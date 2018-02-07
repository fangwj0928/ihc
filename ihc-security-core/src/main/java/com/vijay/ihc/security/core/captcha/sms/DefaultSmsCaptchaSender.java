package com.vijay.ihc.security.core.captcha.sms;

/**
 * 默认短信发送器
 */
public class DefaultSmsCaptchaSender implements SmsCaptchaSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机：" + mobile + ", 发送验证码：" + code);
    }
}
