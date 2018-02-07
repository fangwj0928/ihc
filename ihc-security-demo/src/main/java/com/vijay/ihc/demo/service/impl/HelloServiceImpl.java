package com.vijay.ihc.demo.service.impl;

import com.vijay.ihc.demo.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {

        System.out.println("service");


        return "Hello " + name;
    }
}
