package com.example.keyanzuul.config;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: cj
 * @DateTime: 2020/3/18 21:36
 * @Description: TODO
 */
@Component
public class TokenFiltter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String filterType() {
        System.out.println("filterType");
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        System.out.println("filterOrder");
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1 ;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("shouldFilter");
        try {
            HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
            String requestURI = request.getRequestURI();
            if (requestURI.contains("/login")||requestURI.contains("/loginface")){
                System.out.println("白名单放行");
                return false;
            }
        } catch (Exception e) {
            RequestContext currentContext = RequestContext.getCurrentContext();
            System.out.println("没有认证通过,与redis不一致");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("token is empty");
        }
        return true;
    }

    @Override
    public Object run(){
        System.out.println("run...");
        try {
            RequestContext currentContext = RequestContext.getCurrentContext();
            HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
            String head = request.getHeader("token");
            System.out.println("token:"+head);
            if (head.equals("")){
                System.out.println("没有认证通过,head为null");
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(401);
                currentContext.setResponseBody("token is empty");
            }
            String subject = null;

                Claims claims = jwtUtil.pasertToken(head);
                subject = claims.getSubject();
                System.out.println(subject+"subject");


            String redistoken= (String) redisTemplate.opsForValue().get(subject);
            System.out.println("redistoken:"+redistoken);
            Claims redisclaims1 = jwtUtil.pasertToken(redistoken);
            String redissubject = redisclaims1.getSubject();
            if (subject==null||!redissubject.equals(subject)){
                System.out.println("没有认证通过,与redis不一致");
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(401);
                currentContext.setResponseBody("token is empty");
                return null;
            }else {
               RequestContext requestContext=RequestContext.getCurrentContext();
               requestContext.addZuulRequestHeader("token",redistoken);
            }

        } catch (Exception e) {
            RequestContext currentContext = RequestContext.getCurrentContext();
            System.out.println("没有认证通过,与redis不一致");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("token is empty");
            return null;
        }

        System.out.println("认证通过");
        return null;
    }
}
