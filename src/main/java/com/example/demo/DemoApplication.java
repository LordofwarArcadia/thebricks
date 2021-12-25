package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(DemoApplication.class, args);
    }
}
