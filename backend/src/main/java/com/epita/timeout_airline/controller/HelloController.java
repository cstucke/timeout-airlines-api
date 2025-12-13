package com.epita.timeout_airline.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Timeout Airline Backend Running.";
    }
}
