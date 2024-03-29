package com.example.keyanservice.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @Author: cj
 * @DateTime: 2020/3/17 20:48
 * @Description: 自定义的fastJson的序列化类
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET=Charset.forName("UTF-8");
    private Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }



    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null==t){
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null==bytes||bytes.length<=0){
            return null;
        }
        String str =new String(bytes,DEFAULT_CHARSET);
        return (T)JSON.parseObject(str,clazz);
    }
}
