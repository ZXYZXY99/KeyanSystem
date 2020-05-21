package com.example.keyanzuul.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: cj
 * @DateTime: 2020/3/17 20:44
 * @Description: TODO
 */
@Configuration
public class RedisTemplatConfig {
    @Bean
    public RedisTemplate<Object,Object>  redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> template =new RedisTemplate<>();
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        //value值得序列化采用setValueSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);

        //key值的序列化采用setKeySerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);

        return template;

    }


}
