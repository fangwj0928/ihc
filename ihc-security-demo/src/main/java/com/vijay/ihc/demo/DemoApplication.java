package com.vijay.ihc.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@ComponentScan("com.vijay.ihc")
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.setBannerMode(Banner.Mode.LOG);
        application.run(args);


    }

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }
}
