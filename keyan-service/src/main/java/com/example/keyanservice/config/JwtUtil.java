package com.example.keyanservice.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

/**
 * @Author: cj
 * @DateTime: 2020/3/19 16:24
 * @Description: TODO
 */
@Data
@Configuration
//@ConfigurationProperties("jwt.config")
public class JwtUtil {

    //签名私钥
    private String key="Test";
    //签名的失效时间
    private Long ttl=3600000L;


    public String createjwt(String userName, Map<String,Object> map){
        //设置失效时间
        Long now=System.currentTimeMillis();
        long exp=ttl+now;

        JwtBuilder jwtBuilder =
                Jwts.builder().setSubject(userName).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "Test");
            jwtBuilder.setClaims(map);
            jwtBuilder.setSubject(userName);
            jwtBuilder.setExpiration(new Date(exp));
        String token = jwtBuilder.compact();
        return token;
    }

/**
 * 解析token字符串
 */

public Claims pasertToken(String token){
    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

    return claims;
}


}
