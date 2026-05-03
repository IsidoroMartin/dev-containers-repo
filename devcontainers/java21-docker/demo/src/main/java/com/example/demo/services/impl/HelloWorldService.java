package com.example.demo.services.impl;

import org.springframework.stereotype.Service;

@Service

public class HelloWorldService {

    private static final String HELLO_WORLD = "hello world";

    public String helloWorld() {
        return HELLO_WORLD;
    }
   
}
