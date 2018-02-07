package com.vijay.ihc.security.core.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaProcessorHolder captchaProcessorHolder;

    @GetMapping("/{type}")
    public void create(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws Exception {
        captchaProcessorHolder.findCaptchaProcessor(type).create(new ServletWebRequest(request, response));
    }

}
