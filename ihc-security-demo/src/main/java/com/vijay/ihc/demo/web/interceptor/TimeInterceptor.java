package com.vijay.ihc.demo.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.out.println("处理之前");
        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());

        String name = ((HandlerMethod) handler).getBean().getClass().getName();
        String method = ((HandlerMethod) handler).getMethod().getName();

        System.out.println(name);

        System.out.println(method);


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println("处理中");

        Long startTime = (Long)httpServletRequest.getAttribute("startTime");

        System.out.println("time interceptor: " + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("处理之后");

        Long startTime = (Long)httpServletRequest.getAttribute("startTime");

        System.out.println("time interceptor: " + (System.currentTimeMillis() - startTime));
        System.out.println("ex is " + e);


    }
}
