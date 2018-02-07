package com.vijay.ihc.demo.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.fang.ihc.demo.web.controller.*.*(..))")
    public Object handler(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object[] args = pjp.getArgs();

        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        Object proceed = pjp.proceed();

        System.out.println("time aspect:" + (System.currentTimeMillis() - start));


        return proceed;
    }
}
