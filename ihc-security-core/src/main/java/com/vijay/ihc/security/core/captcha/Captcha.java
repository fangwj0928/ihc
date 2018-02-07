package com.vijay.ihc.security.core.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码类
 */
@Data
@AllArgsConstructor
public class Captcha implements Serializable {

    private static final long serialVersionUID = 5389848076954425844L;

    /**
     * 验证码字符串
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 是否过期
     * @return true：已过期，false：未过期
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    /**
     * 带参构造器
     * @param code 验证码字符串
     * @param expireIn 过期时长，单位秒
     */
    public Captcha(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

}
