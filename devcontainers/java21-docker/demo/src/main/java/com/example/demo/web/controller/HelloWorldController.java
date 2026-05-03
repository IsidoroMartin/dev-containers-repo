package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.impl.HelloWorldService;


@RestController
@RequestMapping("/api/resources/v1")
public class HelloWorldController {
    @Autowired
    private HelloWorldService helloworldservice;
    
    @GetMapping("/greeting")
    public String helloWorld() {
        return helloworldservice.helloWorld();
    }
}
