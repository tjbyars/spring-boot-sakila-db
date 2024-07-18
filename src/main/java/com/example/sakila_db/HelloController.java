package com.example.sakila_db;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/greeting")
    public String sayHello() {
        return "Hello, world!";
    }
}
