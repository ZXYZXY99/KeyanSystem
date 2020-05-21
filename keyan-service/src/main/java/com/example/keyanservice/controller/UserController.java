package com.example.keyanservice.controller;


import com.example.keyanservice.config.JwtUtil;
import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.User;
import com.example.keyanservice.service.IUserService;
import com.example.keyanservice.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/keyanservice/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;

        @GetMapping("/getlist")
        public Result getlist(){
            List<User> userList = userService.getlist();

        return new Result(ResultCode.SUCCESS,userList);
        }


        @PostMapping("/login")

        public Result login(@RequestBody User user){
            System.out.println(user);
            List<User> userList = userService.login(user);

            if (userList.size()==1){
                Map<String,Object> map =new HashMap<>();
                map.put("userName",user.getUserName());
                map.put("Date",new Date());
                String createjwt = jwtUtil.createjwt(user.getUserName(), map);
                //保存到reids中
                redisTemplate.opsForValue().set(user.getUserName(),createjwt);
                redisTemplate.expire(user.getUserName(),60, TimeUnit.MINUTES);
                return new Result(ResultCode.SUCCESS,createjwt);
            }else {
                System.out.println("error");
                return new Result(ResultCode.FAIL);
            }


        }


        @GetMapping("/getusername")
        public Result getusername(){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String head = request.getHeader("token");
            System.out.println(head);
            Claims claims = jwtUtil.pasertToken(head);
            String subject = (String) claims.get("userName");
            return new Result(ResultCode.SUCCESS,subject);
        }


    @PostMapping("/out")
    public Result LoginOut(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("userName");
        redisTemplate.delete(subject);
        return new Result(ResultCode.SUCCESS);
    }


    @PostMapping("/islogin")
    public Result islogin(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("userName");
        List<User> islogin = userService.islogin(subject);
        if (islogin.size()==1){
            return new Result(ResultCode.SUCCESS);
        }else {
            return new Result(ResultCode.LOGINSX);
        }
    }



}
