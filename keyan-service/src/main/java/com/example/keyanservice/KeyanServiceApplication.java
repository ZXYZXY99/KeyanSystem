package com.example.keyanservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.keyanservice.mapper")
public class KeyanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeyanServiceApplication.class, args);
    }

}
