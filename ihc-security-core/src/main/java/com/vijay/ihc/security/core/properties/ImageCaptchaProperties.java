package com.vijay.ihc.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ImageCaptchaProperties extends SmsCaptchaProperties {

    private int width = 88;

    private int height = 24;

}
