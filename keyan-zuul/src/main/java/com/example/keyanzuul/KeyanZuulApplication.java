package com.example.keyanzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class KeyanZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeyanZuulApplication.class, args);
    }

}
