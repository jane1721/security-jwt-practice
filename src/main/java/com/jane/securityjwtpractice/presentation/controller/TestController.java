package com.jane.securityjwtpractice.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, anyone can access this.";
    }

    @GetMapping("/api/secure")
    public String secure() {
        return "Hello, you are authenticated!";
    }
}
