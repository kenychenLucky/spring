package com.kenychen.autotest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Autotest with Spring Boot!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello in Autotest!";
    }

}
