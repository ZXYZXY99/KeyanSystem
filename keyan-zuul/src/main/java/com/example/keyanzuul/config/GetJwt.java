package com.example.keyanzuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cj
 * @DateTime: 2020/3/19 16:47
 * @Description: TODO
 */
@Configuration
public class GetJwt {

    @Bean
    public JwtUtil getJwtUtil(){
      return   new  JwtUtil();
    }
}
