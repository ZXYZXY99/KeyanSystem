package com.example.keyanservice.controller;


import com.example.keyanservice.config.JwtUtil;
import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.Admin;
import com.example.keyanservice.service.impl.AdminServiceImpl;
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
@RequestMapping("/keyanservice/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        System.out.println(admin);
        List<Admin> adminList = adminService.login(admin);
        if (adminList.size()==1){

            Map<String,Object> map =new HashMap<>();
            map.put("adminName",admin.getAdminName());
            map.put("Date",new Date());
            String createjwt = jwtUtil.createjwt(admin.getAdminName(), map);
            //保存到reids中
            redisTemplate.opsForValue().set(admin.getAdminName(),createjwt);
            redisTemplate.expire(admin.getAdminName(),60, TimeUnit.MINUTES);
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
        String subject = (String) claims.get("adminName");
        return new Result(ResultCode.SUCCESS,subject);
    }


    @PostMapping("/out")
    public Result LoginOut(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("adminName");
        redisTemplate.delete(subject);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/islogin")
    public Result islogin(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("adminName");
        List<Admin> islogin = adminService.islogin(subject);
        if (islogin.size()==1){
            return new Result(ResultCode.SUCCESS);
        }else {
            return new Result(ResultCode.LOGINSX);
        }
    }


}
