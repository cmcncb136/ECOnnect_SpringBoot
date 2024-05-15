package com.econnect.econnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/test")
    @ResponseBody
    public String hello() {
        String x = "x" + "322" + "Hello";
        return x;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String test() {
        return "Hello World22";
    }
}

