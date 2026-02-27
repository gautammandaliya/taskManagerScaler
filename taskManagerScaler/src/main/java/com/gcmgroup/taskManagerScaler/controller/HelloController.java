package com.gcmgroup.taskManagerScaler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/v1/welcome")
    String welcome(){
        return "Welcome to Task Manager Using Spring Boot!!!";
    }
}
