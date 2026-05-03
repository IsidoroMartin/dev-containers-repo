package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RestController
public class Controller {

    @Autowired
    private UserService userService;
    @GetMapping("/hello-world")
    public String helloWorld() {
        return new String("Hello world");
    }
    
}
