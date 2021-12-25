package com.example.demo;

import com.codeborne.selenide.Configuration;

@org.springframework.context.annotation.Configuration
public class TestsConfiguration {

    public TestsConfiguration() {
        Configuration.timeout = 10000;
        Configuration.headless = true;
    }
}
