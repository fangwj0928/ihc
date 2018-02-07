package com.vijay.ihc.security.core.captcha.image;

import com.vijay.ihc.security.core.captcha.Captcha;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;

@Data
@EqualsAndHashCode(callSuper=false)
public class ImageCaptcha extends Captcha {

    private static final long serialVersionUID = 7812722437063339088L;

    private BufferedImage image;

    /**
     * 图形验证码
     * @param image 图形对象
     * @param code 验证码字符串
     * @param expireIn 过期时长，单位秒
     */
    public ImageCaptcha(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }
}
