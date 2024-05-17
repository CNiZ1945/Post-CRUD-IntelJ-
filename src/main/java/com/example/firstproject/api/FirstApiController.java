package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Rest API용 Controller
public class FirstApiController {
    @GetMapping("/api/hello") //url 요청 접수
    public String hello() {
        return "Hello World";
    }
}
